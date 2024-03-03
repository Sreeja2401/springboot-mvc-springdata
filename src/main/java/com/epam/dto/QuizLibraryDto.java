package com.epam.dto;

import java.util.List;

import com.epam.entity.QuestionsLibrary;

public class QuizLibraryDto {
	int id;
	String title;
	int marks;
	List<QuestionsLibrary> questionLibrary;
	public QuizLibraryDto(int id,String title, int marks, List<QuestionsLibrary> questionLibrary) {
		super();
		this.id=id;
		this.title = title;
		this.marks = marks;
		this.questionLibrary = questionLibrary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public List<QuestionsLibrary> getQuestionLibrary() {
		return questionLibrary;
	}
	public void setQuestionLibrary(List<QuestionsLibrary> questionLibrary) {
		this.questionLibrary = questionLibrary;
	}
	@Override
	public String toString() {
		return "QuizLibraryDto [title=" + title + ", marks=" + marks + ", questionLibrary=" + questionLibrary + "]";
	}
	
}
