<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-fluid">
	<div class="card mb-3">
		<div class="card-header">
			<i class="fas fa-building"></i> 거래처관리
		</div>
		<div class="card-body">
		  <div class="table-responsive">
		    <div class="text-right mb-3">
	        <button id="retrieveBtn" type="button" class="btn btn-outline-primary">조회</button>
          <button id="createBtn" type="button" class="btn btn-outline-info">추가</button>
	        <button id="modifyBtn" type="button" class="btn btn-outline-success">수정</button>
	      </div>
        <table class="table table-striped table-bordered w-100" id="custTable" >
          <thead>
            <tr>
              <th>거래처코드</th>
              <th>거래처명</th>
              <th>담당자명</th>
              <th>담당자연락처</th>
              <th>담당자핸드폰</th>
              <th>주기(일)</th>
              <th>사용여부</th>
            </tr>
          </thead>
        </table>
      </div>
		</div>
	</div>
</div>
<!-- /.container-fluid -->

<div class="modal fade" id="custModal" tabindex="-1" role="dialog"
  aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">거래처정보 변경</div>
      <div class="modal-body">
        <form name="custForm" id="custForm">
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="custCd" class="form-control" placeholder="거래처코드" readonly>
              <label for="custCd">거리처코드</label>
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
              <input type="tel" id="cusTel" class="form-control" placeholder="연락처" required>
              <label for="cusTel">연락처</label>
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
            <select id="useYn" class="form-control" style="height:48px;">
              <option selected>선택하세요</option>
              <option value="true">사용</option>
              <option value="false">미사용</option>
            </select>
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

<!-- Page level plugin JavaScript-->

<script src="/resources/vendor/datatables/datatables.min.js"></script>

<!-- scripts for this page-->
<script src="/resources/js/agency.js"></script>