<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-fluid">
	<div class="card mb-3">
		<div class="card-header">
			<i class="fas fa-check-circle"></i> 검사일자 관리
		</div>
		<div class="card-body">
			<table class="table table-striped table-bordered w-100" id="checkTable" >
	        	<thead>
	        		<tr>
				        <th>검사일자</th>
				        <th>거래처명</th>
				        <th>담당자명</th>
				        <th>담당자연락처</th>
				        <th>확인자</th>
				        <th>확인일자</th>
	            	</tr>
	          	</thead>
	        </table>
		
		</div>
	</div>

</div>
<!-- /.container-fluid -->

<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
  aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">검사일자 변경</div>
      <div class="modal-body">
        <form name="custForm" id="custForm">
          <div class="form-group">
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="checkDay" class="form-control" placeholder="검사일자">
              <label for="checkDay">검사일자</label>
            </div>
          </div>
            <div class="form-label-group">
              <input type="text" id="custCd" class="form-control" placeholder="거래처코드" readonly>
              <label for="custCd">거래처코드</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group required">
              <input type="text" id="custNm" class="form-control" placeholder="거래처명" required>
              <label for="custNm">거래처명</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group required">
              <input type="text" id="cusName" class="form-control" placeholder="담당자명" required>
              <label for="cusName">담당자명</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group required">
              <input type="tel" id="cusHp" class="form-control" placeholder="핸드폰" required>
              <label for="cusHp">핸드폰</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group required">
              <input type="number" id="cycle" class="form-control" placeholder="주기(일)" required>
              <label for="cycle">주기(일)</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group required">
              <input type="text" id="confNm" class="form-control" placeholder="확인자" required>
              <label for="cycle">확인자</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group required">
              <input type="text" id="confDay" class="form-control" placeholder="확인자" required>
              <label for="cycle">확인일자</label>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
        <button class="btn btn-primary" id="custActionBtn" onclick="handleCustModal()">변경하기</button>
      </div>
    </div>
  </div>
</div>