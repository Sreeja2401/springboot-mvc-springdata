<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewAllQuestions</title>
</head>
<body>
<h2 align=center>available Questions are :</h2>
<table border=1>
<tr>
<th>QuestionNumber</th>
<th>QuestionTitle</th>
<th>Options</th>
<th>DifficultyLevel</th>
<th>TaggingTopics</th>
<th>Answers</th>
<th>Action performed</th>
</tr>
<c:forEach items="${questions}" var="question">
<tr>
<td><c:out value="${question.questionNumber}"/></td>
<td><c:out value="${question.questionTitle}"/></td>
<td><c:out value="${question.options}"/></td>
<td><c:out value="${question.difficultyLevel}"/></td>
<td><c:out value="${question.taggingTopics}"/></td>
<td><c:out value="${question.answers}"/></td>
<td><a href="viewQuestion?questionNumber=${question.questionNumber}">update</a>
<a href="deleteQuestion?questionNumber=${question.questionNumber}">delete</a></td>
</tr>
</c:forEach>
</table>
<a href="createQuestion.jsp">add New Question</a><br>
<a href="viewAllQuiz"> click here for quiz Library</a>
</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewAllQuestions</title>
<style>
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  background-color: #ddd;
}

a {
  color: #000;
  text-decoration: none;
}

a:hover {
  color: #fff;
  background-color: #000;
}
</style>
</head>
<body>
<h2 align=center>available Questions are :</h2>
<table border=1>
<tr>
<th>QuestionNumber</th>
<th>QuestionTitle</th>
<th>Options</th>
<th>DifficultyLevel</th>
<th>TaggingTopics</th>
<th>Answers</th>
<th>Action performed</th>
</tr>
<c:forEach items="${questions}" var="question">
<tr>
<td><c:out value="${question.questionNumber}"/></td>
<td><c:out value="${question.questionTitle}"/></td>
<td><c:out value="${question.options}"/></td>
<td><c:out value="${question.difficultyLevel}"/></td>
<td><c:out value="${question.taggingTopics}"/></td>
<td><c:out value="${question.answers}"/></td>
<td><a href="viewQuestion?questionNumber=${question.questionNumber}">update</a>
<a href="deleteQuestion?questionNumber=${question.questionNumber}">delete</a></td>
</tr>
</c:forEach>
</table>
<a align=center href="createQuestion.jsp">add New Question</a><br>
<a align=center href="viewAllQuiz"> click here for quiz Library</a>
</body>
</html>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewAllQuestions</title>
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #f1f1f1;
}

.container {
  margin: 50px auto;
  max-width: 800px;
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
}

h1 {
  text-align: center;
  margin-bottom: 30px;
}

table {
  border-collapse: collapse;
  width: 100%;
  margin-bottom: 30px;
}

th, td {
  padding: 10px;
  text-align: left;
}

th {
  background-color: #4CAF50;
  color: #fff;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  background-color: #ddd;
}

a {
  display: inline-block;
  padding: 10px 15px;
  background-color: #4CAF50;
  color: #fff;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s;
}

a:hover {
  background-color: #2e8b57;
}
</style>
</head>
<body>
<div class="container">
  <h1>Available Questions</h1>
  <table>
    <tr>
      <th>Question Number</th>
      <th>Question Title</th>
      <th>Options</th>
      <th>Difficulty Level</th>
      <th>Tagging Topics</th>
      <th>Answers</th>
      <th>Action</th>
    </tr>
    <c:forEach items="${questions}" var="question">
      <tr>
        <td><c:out value="${question.questionNumber}"/></td>
        <td><c:out value="${question.questionTitle}"/></td>
        <td><c:out value="${question.options}"/></td>
        <td><c:out value="${question.difficultyLevel}"/></td>
        <td><c:out value="${question.taggingTopics}"/></td>
        <td><c:out value="${question.answers}"/></td>
        <td>
          <a href="viewQuestion?questionNumber=${question.questionNumber}">Update</a>
          <a href="deleteQuestion?questionNumber=${question.questionNumber}">Delete</a>
        </td>
      </tr>
    </c:forEach>
  </table>
  <div style="text-align: center;">
    <a href="createQuestion.jsp">Add New Question</a>
    <a href="viewAllQuiz" style="margin-left: 20px;">View Quiz Library</a>
  </div>
</div>
</body>
</html> --%>

