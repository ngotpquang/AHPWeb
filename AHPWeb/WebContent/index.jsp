<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Quang Ngo">

<title>AHP</title>

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
				<h3>Phương pháp</h3>
			</div>
			<div class="panel-body">
				<form action="Controller" method="get">
					<input type="hidden" name="act" value="getNumOfCriterias">
					<label>Nhập số lượng tiêu chí</label> <input class="form-control"
						type="text" placeholder="Số lượng tiêu chí" name="numOfCriterias">
					<label>Nhập số lượng phương án</label> <input class="form-control"
						type="text" placeholder="Số lượng phương án" name="numOfChoices">
					<hr>
					<div class="col-sm-offset-8 col-sm-2">
						<input class="btn btn-default" type="reset" value="Xóa hết">
					</div>
					<div class="col-sm-2">
						<input class="btn btn-warning" type="submit" value="Tiếp tục">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>