<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
	
<sec:authorize access="isAuthenticated()">
  <c:redirect url="/home" /> 
</sec:authorize>	
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>SEMS</title>
<!-- Bootstrap core CSS-->
<link href="/resources/vendor/bootstrap/css/bootstrap.cosmo.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">

<!-- Custom styles for this template-->
<link href="/resources/css/sems-style.css" rel="stylesheet">
</head>
<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">로그인</div>
			<div class="card-body">
				<form>
					<div class="form-group">
						<div class="form-label-group">
							<input type="text" id="inputUserid" class="form-control"
								placeholder="아이디" required="required" autofocus="autofocus">
							<label for="inputUserid">아이디</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="password" id="inputPassword" class="form-control"
								placeholder="비밀번호" required="required"> <label
								for="inputPassword">비밀번호</label>
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox text-right">
							<label> <input type="checkbox" value="remember-me">
								아이디 저장
							</label>
						</div>
					</div>
					<button type="button" class="btn btn-lg btn-primary btn-block"
						onclick="handleClickLogin()">로그인</button>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="mailto:parkgw@gmail.com">관련문의(Email)</a>
				</div>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap-notify.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="/resources/js/login.js"></script>
</body>
</html>