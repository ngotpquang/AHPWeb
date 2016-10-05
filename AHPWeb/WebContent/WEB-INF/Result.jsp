<%@page import="ahp.utilities.Utilities"%>
<%@page import="ahp.utilities.Matrix"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int numOfCriterias = (int) session.getAttribute("numOfCriterias");
	Matrix criteriaEigenMatrix = (Matrix) request.getAttribute("criteriaEigenMatrix");
	Matrix choiceMatrixAfter = (Matrix) request.getAttribute("choiceMatrixAfter");
	String[] choiceList = (String[]) session.getAttribute("choiceList");
	String[] criteriaList = (String[]) session.getAttribute("criteriaList");
	float[] listCR = (float[]) request.getAttribute("listCR");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Quang Ngo">

<title>AHP - Result</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">

<!-- jQuery -->
<script src="js/jquery.min.js"></script>

<!-- Bootstrap Code Javascript -->
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" style="margin: 15px auto;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3>Phương pháp AHP - Kết quả</h3>
			</div>
			<div class="panel-body">
				<label>Chỉ số thích hợp của các tiêu chí</label>
				<%
					for (int i = 0; i < listCR.length; i++) {
				%>
				<div class="row">
					<div class="col-sm-2">
						<label><%=criteriaList[i]%>:</label>
					</div>
					<div class="col-sm-2">
						<%
							if (listCR[i] < 0.1) {
						%>
						<b style="color: green;"><%=Utilities.formatDecimal(listCR[i])%></b>
						<%
							} else {
						%>
						<b style="color: red;"><%=Utilities.formatDecimal(listCR[i])%></b>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
				%>
				<hr>
				<label>Kết quả</label>
				<%
					Matrix result = Matrix.mutiply(choiceMatrixAfter, criteriaEigenMatrix);
					for (int i = 0; i < result.getNumOfRows(); i++) {
				%>
				<div class="row">
					<div class="col-sm-2">
						<label><%=choiceList[i]%>:</label>
					</div>
					<%
						for (int j = 0; j < result.getNumOfCols(); j++) {
					%>
					<div class="col-sm-4">
						<%
							if (result.getData()[i][j] < Utilities.getMaxInMatrixData(result)) {
						%>
						<b style="color: blue;"><%=Utilities.formatDecimal(result.getData()[i][j])%></b>
						<%
							} else {
						%>
						<b style="color: violet;"><%=Utilities.formatDecimal(result.getData()[i][j])%>
							(Chọn phương án này)</b>
						<%
							}
						%>
					</div>
					<%
						}
					%>
				</div>
				<%
					}
				%>
				<hr>
				<div class="col-sm-offset-5 col-sm-2">
					<a href="Controller?act=home" type="button" class="btn btn-primary">Trang chủ</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>