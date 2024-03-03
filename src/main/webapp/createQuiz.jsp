<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

h1 {
  text-align: center;
  color: black;   /* change the text color to red */
}


form {
	margin: 0 auto;
	width: 50%;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0px 2px 5px #888;
	border-radius: 5px;
}

label {
	display: block;
	font-size: 18px;
	margin-bottom: 10px;
	color: #333;
}

input[type="text"], input[type="number"] {
	width: 100%;
	padding: 10px;
	border-radius: 5px;
	border: none;
	background-color: #f2f2f2;
	margin-bottom: 20px;
	font-size: 16px;
	color: #333;
}

input[type="checkbox"] {
	margin-right: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th {
	padding: 10px;
	text-align: center;
	background-color: grey; /* change the background color to blue */
	color: #fff;
}

td {
	padding: 10px;
	border-bottom: 1px solid #ddd;
	color: #333;
}

input[type="submit"] {
	display: block;
	margin: 0 auto;
	padding: 10px 20px;
	background-color: grey;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: 18px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #2E8B57;
}
</style>
</head>
<body align="center">
	<h1>Create Quiz</h1>
	<form action="createQuiz" method="POST">
		Enter Quiz Title:<input type="text" name="title" id="title"><br>
		<br> Enter marks:<input type="number" name="marks" id="marks"><br>
		<h3>select questions</h3>
		<table border="2" align="center">
			<tr>
				<th>SELECT</th>
				<th>ID</th>
				<th>TITLE</th>

				<c:forEach items="${questionList}" var="q">
					<tr>
						<td><input type="checkbox" name="questionIds"
							value="${q.questionNumber}"></td>
						<td><c:out value="${q.questionNumber}" /></td>
						<td><c:out value="${q.questionTitle}" /></td>

					</tr>
				</c:forEach>
		</table>
		<br> <input type="submit" value="Create">
	</form>
</body>
</html>