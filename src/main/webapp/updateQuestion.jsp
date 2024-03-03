<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Questions</title>
</head>
<body>
	<h2> update Questions</h2>
	<form action="updateQuestion" method="post">
	    <input type="hidden" name="questionNumber" value="${question.questionNumber}" />
		Question title :<input type=text name="questionTitle" value="${question.questionTitle}"><br><br>
		options:<input type=text name="options" value="${question.options}"><br><br>
		level:<input type=text name="difficultyLevel" value="${question.difficultyLevel}"><br><br>
		TaggingTopics: <input type=text name="taggingTopics" value="${question.taggingTopics}"><br><br>
		Answers: <input type=text name="answers" value="${question.answers}"><br><br>
		<input type="submit" value="update  Question">
	</form>
</body>
</html>