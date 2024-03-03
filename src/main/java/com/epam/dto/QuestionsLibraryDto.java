package com.epam.dto;

import java.util.List;

public class QuestionsLibraryDto {
	String questionTitle;
     List<String> options;
	String difficultyLevel;
	String taggingTopics;
	String answers;
	public QuestionsLibraryDto(String questionTitle, List<String> options, String difficultyLevel, String taggingTopics,
			String answers) {
		super();
		this.questionTitle = questionTitle;
		this.options = options;
		this.difficultyLevel = difficultyLevel;
		this.taggingTopics = taggingTopics;
		this.answers = answers;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public String getTaggingTopics() {
		return taggingTopics;
	}
	public void setTaggingTopics(String taggingTopics) {
		this.taggingTopics = taggingTopics;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	
}
