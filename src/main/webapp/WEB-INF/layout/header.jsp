<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
	<a class="navbar-brand mr-1" href="/">SEMS</a>

	<button class="btn btn-link btn-sm text-white order-1"
		id="sidebarToggle">
		<i class="fas fa-bars"></i>
	</button>
	
	<!-- Navbar -->
	<ul class="navbar-nav ml-auto">
		<li class="nav-item dropdown no-arrow">
       <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <sec:authentication property="principal.name" scope="request" />
         (<sec:authentication property="principal.jw" scope="request" />)
         <i class="fas fa-user-circle fa-fw"></i>
       </a>
       <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
         <a class="dropdown-item" href="#" data-toggle="modal" data-target="#profileModal">사용자정보 변경</a>
         <div class="dropdown-divider"></div>
         <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">로그아웃</a>
       </div>
     </li>
	</ul>
	
	
</nav>

<div id="wrapper"> <!-- footer.jsp 에서 완료 됨 -->
	<!-- Sidebar -->
	<ul id="sidebar-nav" class="sidebar navbar-nav toggled">
		<li class="nav-item"><a class="nav-link" href="/home">
				<i class="fas fa-fw fa-home"></i> <span>홈</span>
		</a>
		</li>
		<li class="nav-item">
		  <a class="nav-link" href="/agency"><i class="fas fa-fw fa-building"></i><span>거래처관리</span></a>
		</li>
		<li class="nav-item">
		  <a class="nav-link" href="/check"><i class="fas fa-check-circle"></i><span>검사일자<br/>관리</span></a>
		</li>
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="pagesDropdown" role="button" data-toggle="dropdown"
			aria-haspopup="true" aria-expanded="false"> <i
				class="fas fa-fw fa-folder"></i> <span>서브메뉴</span>
		</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown">
				<h6 class="dropdown-header">메뉴그룹1</h6>
				<a class="dropdown-item" href="/">메뉴1</a>
				<a class="dropdown-item" href="/">메뉴2</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">메뉴그룹2</h6>
				<a class="dropdown-item" href="/">메뉴1</a> 
				<a class="dropdown-item" href="/">메뉴2</a>
			</div></li>
		<li class="nav-item"><a class="nav-link" href="/chart">
				<i class="fas fa-fw fa-chart-area"></i> <span>차트</span> 
		</a></li>
		<li class="nav-item"><a class="nav-link" href="/grid">
				<i class="fas fa-fw fa-table"></i> <span>그리드</span>
		</a></li>
	</ul>
	
	<div id="content-wrapper">