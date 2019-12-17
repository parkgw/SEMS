$(document).ready(function(){
	$('#inputPassword').keydown(function(e) {
		if (e.keyCode === 13) {
			handleClickLogin();
		}
	});
})

function handleClickLogin() {
  const userid = $('#inputUserid');
  const passwd = $('#inputPassword');
  
  if (userid.val() === '') {
	  toast('warning', '아이디를 입력해주세요.');
	  userid.focus();
	  
	  return;
  }
  
  if (passwd.val() === '') {
	  toast('warning', '비밀번호를 입력해주세요.');
	  passwd.focus();
	  
	  return;
  }
  
  const params = new URLSearchParams();
  params.append('userid', userid.val());
  params.append('passwd', passwd.val());
  
  axios({
    method: 'post',
    url: '/login',
    data: params
  })
  .then(function (response) {
    console.log(response);
    
    location.href = "/";
  })
  .catch(function (error) {
    console.log(error);
    
    const message = error.response.data.message;
    
    if (message !== undefined) {
    	toast('danger', message);
    }
  });
}

function toast(type, message) {
	$.notify({
		message
	}, {
		type,
		placement: {
			from: 'bottom',
			align: 'center',
		}
	});
}
