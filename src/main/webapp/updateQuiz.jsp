<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
	<h1>Create Quiz</h1>
	<form action="updateQuiz" method="POST">
	    <input type="hidden" name="id" value="${quiz.id}" />
		Enter Quiz Title:<input type="text" name="title" id="title" value="${quiz.title}" ><br>
		<br> Enter marks:<input type="number" name="marks" id="marks" value="${quiz.marks}" ><br>
		<h3>select questions</h3>
		<table border="2" align="center">
			<tr>
				<th>SELECT</th>
				<th>ID</th>
				<th>TITLE</th>
				<c:forEach items="${questionList}" var="q">
					<tr>
						<td><input type="checkbox" name="questionIds"
							value="${q.questionNumber}" <c:if test="${quiz.questionLibrary.contains(q)}">checked</c:if>></td>
						<td><c:out value="${q.questionNumber}" /></td>
						<td><c:out value="${q.questionTitle}" /></td>
						
					</tr>
				</c:forEach>
				
		</table>
		<br> <input type="submit" value="update">
	</form>
</body>
</html>