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
	<form action="countMarks" method="post">
		<input type="hidden" name="title" value="${title}" />
		<c:forEach items="${questionList}" var="question">
		<h4>${question.questionNumber}) ${question.questionTitle}</h4>
		<select id="users" name="options" id="options" required="required">
		<c:forEach items="${question.options}" var="options">
		<option value="${options}">${options}</option>    
   </c:forEach>
			</select>
		</c:forEach>
		<input type="submit" value="submit">
	</form>
</body>
</html>