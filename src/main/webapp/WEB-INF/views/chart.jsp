<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-fluid">
	<!-- Area Chart Example-->
	<div class="card mb-3">
		<div class="card-header">
			<i class="fas fa-chart-area"></i> Area Chart Example
		</div>
		<div class="card-body">
			<canvas id="myAreaChart" width="100%" height="30"></canvas>
		</div>
		<div class="card-footer small text-muted">Updated yesterday at
			11:59 PM</div>
	</div>

	<div class="row">
		<div class="col-lg-8">
			<div class="card mb-3">
				<div class="card-header">
					<i class="fas fa-chart-bar"></i> Bar Chart Example
				</div>
				<div class="card-body">
					<canvas id="myBarChart" width="100%" height="50"></canvas>
				</div>
				<div class="card-footer small text-muted">Updated yesterday at
					11:59 PM</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="card mb-3">
				<div class="card-header">
					<i class="fas fa-chart-pie"></i> Pie Chart Example
				</div>
				<div class="card-body">
					<canvas id="myPieChart" width="100%" height="100"></canvas>
				</div>
				<div class="card-footer small text-muted">Updated yesterday at
					11:59 PM</div>
			</div>
		</div>
	</div>
</div>
<!-- /.container-fluid -->

<!-- Page level plugin JavaScript-->
<script src="/resources/vendor/chart.js/Chart.min.js"></script>

<!-- Demo scripts for this page-->
<script src="/resources/js/demo/chart-area-demo.js"></script>
<script src="/resources/js/demo/chart-bar-demo.js"></script>
<script src="/resources/js/demo/chart-pie-demo.js"></script>