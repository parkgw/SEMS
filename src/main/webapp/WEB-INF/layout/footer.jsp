<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

    <!-- Sticky Footer -->
		<footer class="sticky-footer">
			<div class="container my-auto">
				<div class="copyright text-center my-auto">
					<span>Copyright &copy; SEMS 2019 </span>
				</div>
			</div>
		</footer>
	</div>
</div>
<!-- /#wrapper -->


<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">알림</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">로그아웃 하시겠습니까?</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
				<a class="btn btn-primary" href="/logout">로그아웃</a>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="profileModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">사용자정보 변경</div>
			<div class="modal-body">
				<form name="profileForm" id="profileForm">
					<div class="form-group">
						<div class="form-label-group">
							<input type="text" id="profileUserid" class="form-control"
								readonly value="<sec:authentication property='principal.username' />">
							<label for="profileUserid">아이디</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group required">
							<input type="password" id="profileOldPasswd" class="form-control"
								placeholder="기존 비밀번호" required="required"> <label
								for="profileOldPasswd">기존 비밀번호</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="password" id="profileNewPasswd" class="form-control"
								placeholder="새로운 비밀번호"> <label
								for="profileNewPasswd">새로운 비밀번호</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="password" id="profileNewPasswdRe"
								class="form-control" placeholder="새로운 비밀번호 확인"
								> <label for="profileNewPasswdRe">새로운
								비밀번호 확인</label>
						</div>
					</div>
					<div class="form-group">
					  <span id="currentJw" style="display:none;"><sec:authentication property="principal.jw"/></span>
						<label for="profileJw">직위</label>
						<select id="profileJw" class="form-control">
						  <option selected>선택하세요</option>
							<option value="인턴">인턴</option>
							<option value="사원">사원</option>
							<option value="선임">선임</option>
							<option value="주임">주임</option>
							<option value="계장">계장</option>
							<option value="대리">대리</option>
							<option value="과장">과장</option>
							<option value="차장">차장</option>
							<option value="부장">부장</option>
							<option value="이사">이사</option>
							<option value="상무">상무</option>
							<option value="전무">전무</option>
							<option value="부사장">부사장</option>
							<option value="사장">사장</option>
							<option value="부회장">부회장</option>
							<option value="회장">회장</option>
						</select>
					</div>
					<div class="form-group">
					  <span id="currentAuth" style="display:none"><sec:authentication property="principal.authorities" /></span>
					  <label for="profileAuth">권한</label>
						<div class="form-check">
						  <input class="form-check-input" name="profileAuth" type="checkbox" value="A" id="profileAuth1">
						  <label class="form-check-label" for="profileAuth1">
						        일반사용자
						  </label>
						</div>
						<div class="form-check">
              <input class="form-check-input" name="profileAuth" type="checkbox" value="B" id="profileAuth2">
              <label class="form-check-label" for="profileAuth2">
                                   담당자
              </label>
            </div>
            <div class="form-check">
              <input class="form-check-input" name="profileAuth" type="checkbox" value="C" id="profileAuth3">
              <label class="form-check-label" for="profileAuth3">
                                  시스템관리자
              </label>
            </div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
				<button class="btn btn-primary" id="profileModifyBtn" onclick="handleProfileModify()">변경하기</button>
			</div>
		</div>
	</div>
</div>