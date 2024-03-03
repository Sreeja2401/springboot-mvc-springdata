<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1>
<tr>
<th>QuizNumber</th>
<th>QuizTitle</th>
<th>marks</th>
<th>questionLibrary</th>
<th>Action performed</th>
</tr>
<c:forEach items="${quizList}" var="quiz">
<tr>
<td><c:out value="${quiz.id}"/></td>
<td><c:out value="${quiz.title}"/></td>
<td><c:out value="${quiz.marks}"/></td>
<td><c:out value="${quiz.questionLibrary}"/></td>
<td><a href="viewQuiz?id=${quiz.id}">update</a>
<a href="deletequiz?id=${quiz.id}">delete</a></td>
</tr>
</c:forEach>
</table>
<a href="createQuiz.jsp">add New Quiz</a><br>
<a href="viewAllQuestions"> click here for question Library</a>
</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz List</title>
</head>
<body>
<h2 align=center>available Quizzes  are :</h2>
<table style="border-collapse: collapse; width: 100%; margin-bottom: 20px;">
<tr style="background-color: #f2f2f2;">
<th style="padding: 8px; text-align: left; border: 1px solid #ddd;">QuizNumber</th>
<th style="padding: 8px; text-align: left; border: 1px solid #ddd;">QuizTitle</th>
<th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Marks</th>
<th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Question Library</th>
<th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Actions</th>
</tr>
<c:forEach items="${quizList}" var="quiz">
<tr>
<td style="padding: 8px; text-align: left; border: 1px solid #ddd;"><c:out value="${quiz.id}"/></td>
<td style="padding: 8px; text-align: left; border: 1px solid #ddd;"><c:out value="${quiz.title}"/></td>
<td style="padding: 8px; text-align: left; border: 1px solid #ddd;"><c:out value="${quiz.marks}"/></td>
<td style="padding: 8px; text-align: left; border: 1px solid #ddd;"><c:out value="${quiz.questionLibrary}"/></td>
<td style="padding: 8px; text-align: left; border: 1px solid #ddd;">
<a style="text-decoration: none; margin-right: 10px;" href="viewQuiz?id=${quiz.id}">Update</a>
<a style="text-decoration: none;" href="deletequiz?id=${quiz.id}">Delete</a>
</td>
</tr>
</c:forEach>
</table>
<a href="addQuestionsToQuiz" style="text-decoration: none;">Add New Quiz</a><br>
<a href="viewAllQuestions" style="text-decoration: none;">Click Here for Question Library</a>
</body>
</html>
