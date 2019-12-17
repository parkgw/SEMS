$(document).ready(function(){
	initProfileModal();
	sidebarActiveInit();
});

function initProfileModal() {
	$('#profileModal').on('shown.bs.modal', function() {
		// 직위 초기 설정
		$('#profileJw').val($('#currentJw').html());
		
		// 권한 초기 설정
		const currentAuth = $('#currentAuth').html();
		const roles = currentAuth.slice(1, currentAuth.length - 1).replace(/ROLE_/gi, '').split(", ");
		roles.map(function(role) {
			$('input:checkbox[name=profileAuth]').each(function() {
				const checkboxVal = $(this).val();
				if (role === checkboxVal) {
					$(this).prop('checked', true);
				}
			});
		});
	});
}

function sidebarActiveInit() {
	const pathname = location.pathname;
    $('#sidebar-nav li a').each(function(){
        var $this = $(this);
        if($this.attr('href').indexOf(pathname) !== -1){
            $this.parent().addClass('active');
        }
    })
}

function handleProfileModify() {
	const userid = $('#profileUserid').val();
	const oldPasswd = $('#profileOldPasswd').val();
	const newPasswd = $('#profileNewPasswd').val();
	const newPasswdRe = $('#profileNewPasswdRe').val();
	const jw = $('#profileJw').val();
	let roles = [];
	
	if (oldPasswd === '') {
		toast('danger', '기존 비밀번호를 입력 후 다시 시도 바랍니다.');
		$('#profileOldPasswd').focus();
		
		return;
	}
	
	// 새로운 비밀번호가 입력된 경우 입력한 두 값이 같아야 한다.
	if (newPasswd !== '' || newPasswdRe !== '') {
		if (newPasswd.length <= 10 || newPasswd.length)
		if (newPasswd !== newPasswdRe) {
			toast('danger', '새로운 비밀번호가 일치하지 않습니다.');
			
			return;
		}
	}
	
	$('input:checkbox[name=profileAuth]').each(function() {
		const checkboxVal = $(this).val();
		if ($(this).is(':checked')) {
			roles.push($(this).val());
		}
	});
	
	axios({
		method: 'put',
		url: '/users/' + userid,
		data: {
			userid,
			oldPasswd,
			newPasswd,
			newPasswdRe,
			jw,
			auth: roles.join(',')
		}
	})
	.then(function (response) {
		console.log(response);
		
		$('#profileOldPasswd').val('');
		$('#profileModal').modal('toggle');
		
		toast('primary', '성공적으로 변경을 완료하였습니다.');
	})
	.catch(function (error) {
		console.log(error.response);
		
		const { errCode, errMessage } = error.response.data;
		
		toast('danger', errMessage);
		if (errCode === 'newPasswd') {
			$('#profileNewPasswd').focus();
		} else if (errCode === 'oldPasswd') {
			$('#profileOldPasswd').focus();
		}
	});
}

function toast(type, message) {
	// 이미 열려있는 창 닫기
	$('div[data-notify=container]').remove();
	
	$.notify({
		message
	}, {
		type,
		placement: {
			from: 'bottom',
			align: 'center',
		},
		allow_dismiss: true,
		animate: {
			enter: 'animated fadeInRight',
			exit: 'animated fadeOutRight'
		}
	});
}
