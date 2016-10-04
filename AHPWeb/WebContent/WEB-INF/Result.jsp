<%@page import="ahp.utilities.Utilities"%>
<%@page import="ahp.utilities.Matrix"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int numOfCriterias = (int) session.getAttribute("numOfCriterias");
	Matrix criteriaEigenMatrix = (Matrix) session.getAttribute("criteriaEigenMatrix");
	Matrix choiceMatrixAfter = (Matrix) session.getAttribute("choiceMatrixAfter");
	String[] choiceList = (String[]) session.getAttribute("choiceList");
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
				<p>ChoiceMatrixAfter</p>
				<%
					for (int i = 0; i < choiceMatrixAfter.getNumOfRows(); i++) {
				%>
				<div class="row">
					<%
						for (int j = 0; j < choiceMatrixAfter.getNumOfCols(); j++) {
					%>
					<div class="col-sm-1">
						<input class="form-control" type="text"
							value="<%=Utilities.formatDecimal(choiceMatrixAfter.getData()[i][j])%>" disabled>
					</div>
					<%
						}
					%>
				</div>
				<br />
				<%
					}
				%>
				
				<p>CriteriaEigenMatrix</p>
				<%
					for (int i = 0; i < criteriaEigenMatrix.getNumOfRows(); i++) {
				%>
				<div class="row">
					<%
						for (int j = 0; j < criteriaEigenMatrix.getNumOfCols(); j++) {
					%>
					<div class="col-sm-1">
						<input class="form-control" type="text"
							value="<%=Utilities.formatDecimal(criteriaEigenMatrix.getData()[i][j])%>" disabled>
					</div>
					<%
						}
					%>
				</div>
				<br />
				<%
					}
				%>
				
				
				<p>Result</p>
				<%	
					Matrix result = Matrix.mutiply(choiceMatrixAfter, criteriaEigenMatrix);
					for (int i = 0; i < result.getNumOfRows(); i++) {
				%>
				<div class="row">
					<div class="col-sm-2">
						<input class="form-control" type="text" value="<%=choiceList[i] %>" disabled>
					</div>
					<%
						for (int j = 0; j < result.getNumOfCols(); j++) {
					%>
					<div class="col-sm-1">
						<input class="form-control" type="text"
							value="<%=Utilities.formatDecimal(result.getData()[i][j])%>" disabled>
					</div>
					<%
						}
					%>
				</div>
				<br />
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>