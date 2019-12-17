let custTable;
let selectedData;
let mode;

$(document).ready(function(){
	initDataTable();
	
	$('#retrieveBtn').click(function() {
		retrieve();
	});
	
	$('#modifyBtn').click(function() {
		modalControl('modify');
	});
	
	$('#createBtn').click(function() {
		modalControl('create');
	});
});

function modalControl(type) {
	if (type === 'create') {
		$('#custModal .modal-header').html('거래처 등록');
		$('#custModal #custActionBtn').html('등록하기');
		$('#custModal').modal('show');
		
		$('#custCd').val('');
		$('#custNm').val('');
		$('#cusName').val('');
		$('#cusTel').val('');
		$('#cusHp').val('');
		$('#cycle').val('');
		$('#useYn').val('true');
		
		mode = 'create';
	} else if (type === 'modify') {
		if (selectedData !== undefined) {
			$('#custModal .modal-header').html('거래처정보 변경');
			$('#custModal #custActionBtn').html('변경하기');
			$('#custModal').modal('show');
			
			$('#custCd').val(selectedData.custCd);
			$('#custNm').val(selectedData.custNm);
			$('#cusName').val(selectedData.cusName);
			$('#cusTel').val(selectedData.cusTel);
			$('#cusHp').val(selectedData.cusHp);
			$('#cycle').val(selectedData.cycle);
			$('#useYn').val(selectedData.useYn ? 'true' : 'false');
			
			mode = 'modify';
		} else {
			toast('warning', '거래처 선택 후 다시 시도 바랍니다.');
		}
	}
}

function initDataTable() {
	custTable = $('#checkTable').DataTable({
		processing: true,
		serverSide: false,
		ajax: {
			url: '/checkList',
			type: 'get',
			dataSrc: ''
		},
		select: true,
		ordering: [1, 'desc'],
		lengthChange: false,
		language: {
			info: '총 _TOTAL_개의 내역이 조회되었습니다.',
			infoEmpty: '조회된 내역이 없습니다.',
			search: '검색',
		    zeroRecords: '내역이 없습니다.',
		    infoFiltered: '',
		    processing: '처리중',
		    paginate: {
		    	first: '처음',
		    	last: '마지막',
		    	next: '다음',
		    	previous: '이전'
		    },
		    select: {
		    	rows: ''
		    }
		},
		columns: [
			{ data: 'custCd' },
			{ data: 'custNm' },
			{ data: 'cusName' },
			{ data: 'cusTel' },
			{ data: 'cusHp' },
			{ data: 'cycle' },
			{ data: 'useYn' }
		],
		rowCallback: function( row, data ) {
			$('td:eq(6)', row).html(data.useYn ? '사용' : '미사용');
		}
	});
	
	$('#custTable tbody').on('click', 'tr', function (){
    	selectedData = Object.is(selectedData, custTable.row(this).data()) ? 
    			undefined : custTable.row(this).data();
    });
}

function retrieve() {
	custTable.ajax.reload();
	selectedData = undefined;
}

function handleCustModal() {
	let baseUrl = '/agencies';
	let url;
	let method;
	
	// Validation check (required)
	let isValid = true;
	$('form#custForm').find('input').each(function(){
	    if($(this).prop('required') && $(this).val() === ''){
	    	$(this).focus();
	    	toast('warning', this.labels[0].innerText + '을 입력 후 다시 시도 바랍니다.');
	    	
	    	isValid = false;
	    	return false;
	    } 
	});
	if (!isValid) return;
	
	if (mode === 'create') {
		method = 'post';
		url = baseUrl;
	} else if (mode === 'modify') {
		let { custCd } = selectedData;
		method = 'put';
		url = baseUrl + '/' + custCd;
	} else {
		toast('warning', '알 수 없는 요청입니다.');
	}
	
	axios({
		method,
		url,
		data: {
			custCd: +$('#custCd').val(),
			custNm: $('#custNm').val(),
			cusName: $('#cusName').val(),
			cusTel: $('#cusTel').val(),
			cusHp: $('#cusHp').val(),
			cycle: +$('#cycle').val(),
			useYn: $('#useYn').val()
		}
	})
	.then(function (response) {
		console.log(response);
		
		$('#custModal').modal('toggle');
		toast('primary', response.data.message);
		
		// 성공적으로 처리 후 재조회
		custTable.ajax.reload();
		selected
	})
	.catch(function (error) {
		console.log(error);
		if (error.response && error.response.data && error.response.data.errMessage) {
			toast('danger', error.response.data.errMessage);
		} else {
			toast('danger', '오류가 발생하였습니다.');
		}
	});
}
