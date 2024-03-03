<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Questions</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

h2 {
	text-align: center;
	color: black;
	margin-top: 50px;
}

form {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	border-radius: 5px;
}

label {
	display: block;
	margin-bottom: 10px;
	font-weight: bold;
}

input[type="text"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
	box-sizing: border-box;
	margin-bottom: 20px;
}

input[type="submit"] {
	background-color: lavender;
	color: purple;
	padding: 10px 20px;
	border: none;
	border-radius: 3px;
	cursor: pointer;
	font-size: 16px;
}

input[type="submit"]:hover {
	background-color: grey;
}
</style>
</head>
<body>
	<h2>Create Questions</h2>
	<form action="addQuestions" method="post">
		<label for="questionTitle">Question title:</label> <input type="text"
			name="questionTitle" id="questionTitle" required> <label
			for="options">Options:</label> <input type="text" name="options"
			id="options" required> <label for="difficultyLevel">Level:</label>
		<select name="difficultyLevel" id="difficultyLevel" required>
			<option value="">Choose a level</option>
			<option value="easy">Easy</option>
			<option value="medium">Medium</option>
			<option value="hard">Hard</option>
		</select><br>
		<br> <label for="taggingTopics">Tagging Topics:</label> <input
			type="text" name="taggingTopics" id="taggingTopics" required>

		<label for="answers">Answers:</label> <input type="text"
			name="answers" id="answers" required> <input type="submit"
			value="Add Question">
	</form>
</body>
</html>
