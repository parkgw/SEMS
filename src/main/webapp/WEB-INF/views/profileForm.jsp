<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>   

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="_csrf" content="${_csrf.token}" >
<meta name="_csrf_header" content="${_csrf.headerName}" >
<title>SEMS</title>
<!-- Bootstrap core CSS-->
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<!-- Page level plugin CSS-->
<link href="/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/resources/css/sems-style.css" rel="stylesheet">
</head>
<body class="sidebar-toggled">
	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
	  <a class="navbar-brand mr-1" href="index.html">SEMS</a>
	
	  <button class="btn btn-link btn-sm text-white order-1"
	    id="sidebarToggle" href="#">
	    <i class="fas fa-bars"></i>
	  </button>
  </nav>
	
	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	
	<!-- Page level plugin JavaScript-->
	<script src="/resources/vendor/chart.js/Chart.min.js"></script>
	<script src="/resources/vendor/datatables/jquery.dataTables.js"></script>
	<script src="/resources/vendor/datatables/dataTables.bootstrap4.js"></script>
	
	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin.min.js"></script>
</body>
</html>