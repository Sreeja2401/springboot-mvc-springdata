package com.epam.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class QuestionsLibrary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int questionNumber;
	String questionTitle;
	@ElementCollection(targetClass=String.class)
	public List<String> options;
	String difficultyLevel;
	String taggingTopics;
	String answers;
	public QuestionsLibrary(String questionTitle, List<String> options, String difficultyLevel, String taggingTopics,
			String answers) {
		super();
		this.questionTitle = questionTitle;
		this.options = options;
		this.difficultyLevel = difficultyLevel;
		this.taggingTopics = taggingTopics;
		this.answers = answers;
	}
	public QuestionsLibrary() {
		super();
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
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
	@Override
	public String toString() {
		return "QuestionsLibrary [questionNumber=" + questionNumber + ", questionTitle=" + questionTitle + ", options="
				+ options + ", difficultyLevel=" + difficultyLevel + ", taggingTopics=" + taggingTopics + ", answers="
				+ answers + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(answers, difficultyLevel, options, questionNumber, questionTitle, taggingTopics);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionsLibrary other = (QuestionsLibrary) obj;
		return Objects.equals(answers, other.answers) && Objects.equals(difficultyLevel, other.difficultyLevel)
				&& Objects.equals(options, other.options) && questionNumber == other.questionNumber
				&& Objects.equals(questionTitle, other.questionTitle)
				&& Objects.equals(taggingTopics, other.taggingTopics);
	}
	
}