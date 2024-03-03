package com.epam.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.controller.QuestionsLibraryController;
import com.epam.customexceptions.DuplicateQuestionFoundException;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.entity.QuestionsLibrary;
import com.epam.service.QuestionsLibraryService;

@WebMvcTest(QuestionsLibraryController.class)
public class QuestionsLibraryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private QuestionsLibraryService questionOperation;

	QuestionsLibrary question1;
	QuestionsLibrary question2;
	List<QuestionsLibrary> availableQuestionList;

	@BeforeEach
	public void setUp() {
		question1 = new QuestionsLibrary("vjit location?", Arrays.asList("aziznagar", "b", "c"), "low", "collage",
				"aziznagar");
		question2 = new QuestionsLibrary("cbit location?", Arrays.asList("gandipet", "b", "c"), "low", "collage",
				"gandipet");
		availableQuestionList = new ArrayList<>(Arrays.asList(question1, question2));
	}

	@Test
	void createQuestionTest() throws Exception {
		Mockito.when(questionOperation.save(any())).thenReturn(question1);
		mockMvc.perform(post("/addQuestions")).andExpect(view().name("questionSucess.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "question created  succesfully")).andReturn();

	}

	@Test
	void createQuestionWithExistingTitleTest() throws Exception {
		Mockito.when(questionOperation.save(any()))
				.thenThrow(new DuplicateQuestionFoundException("question with given title already exist"));
		mockMvc.perform(post("/addQuestions")).andExpect(view().name("exception.jsp"))
				.andExpect(model().attributeExists("exception"))
				.andExpect(model().attribute("exception", "question with given title already exist")).andReturn();
	}

	@Test
	void viewAllQuestionsTest() throws Exception {
		Mockito.when(questionOperation.viewAll()).thenReturn(availableQuestionList);
		mockMvc.perform(get("/viewAllQuestions")).andExpect(view().name("viewAllQuestions.jsp"))
				.andExpect(model().attributeExists("questions"))
				.andExpect(model().attribute("questions", questionOperation.viewAll())).andReturn();
	}

	@Test
	void viewQuestionByIdTest() throws Exception {
		int questionNumber = 1;
		Mockito.when(questionOperation.viewById(questionNumber)).thenReturn(question1);
		mockMvc.perform(get("/viewQuestion").param("questionNumber", "1")).andExpect(view().name("updateQuestion.jsp"))
				.andExpect(model().attributeExists("question"))
				.andExpect(model().attribute("question", questionOperation.viewById(questionNumber))).andReturn();
	}

	@Test
	void viewQuestionByNonExistingId() throws Exception {
		int questionNumber = 1;
		Mockito.when(questionOperation.viewById(questionNumber))
				.thenThrow(new QuestionIdNotFoundException("id not found"));
		mockMvc.perform(get("/viewQuestion").param("questionNumber", "1")).andExpect(view().name("exception.jsp"))
				.andExpect(model().attributeExists("exception"))
				.andExpect(model().attribute("exception", "id not found")).andReturn();
	}

	@Test
	void deleteQuestionTest() throws Exception {
		int questionNumber = 1;
		Mockito.when(questionOperation.delete(questionNumber)).thenReturn(question1);
		mockMvc.perform(post("/deleteQuestion").param("questionNumber", "1"))
				.andExpect(view().name("questionSucess.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "question deleted  succesfully")).andReturn();
	}

	@Test
	void deleteQuestionByNotExistingId() throws Exception {
		int questionNumber = 1;
		Mockito.when(questionOperation.delete(questionNumber))
				.thenThrow(new QuestionIdNotFoundException("id not found"));
		mockMvc.perform(post("/deleteQuestion").param("questionNumber", "1")).andExpect(view().name("exception.jsp"))
				.andExpect(model().attributeExists("exception"))
				.andExpect(model().attribute("exception", "id not found")).andReturn();
	}

	@Test
	void updateQuestionTest() throws Exception {
		Mockito.when(questionOperation.updateQuestion(question1)).thenReturn(question1);
		mockMvc.perform(post("/updateQuestion")).andExpect(view().name("questionSucess.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "updated question successfully")).andReturn();
	}

}
