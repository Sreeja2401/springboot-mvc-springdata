package com.epam.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.controller.QuizLibraryController;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.customexceptions.QuizNotFoundException;
import com.epam.dto.QuizLibraryDto;
import com.epam.entity.QuestionsLibrary;
import com.epam.entity.QuizLibrary;
import com.epam.service.QuestionsLibraryService;
import com.epam.service.QuizLibraryService;

@WebMvcTest(QuizLibraryController.class)
class QuizLibraryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuizLibraryService quizService;

	@MockBean
	private QuestionsLibraryService questionsService;

	List<QuizLibrary> quizList;
	QuizLibrary quiz1;
	QuizLibraryDto quiz1Dto;

	@BeforeEach
	public void setUp() {
		quiz1 = new QuizLibrary("colleges", 65,
				Arrays.asList(
						new QuestionsLibrary("vjit location", Arrays.asList("a", "b", "c"), "low", "collage", "a"),
						new QuestionsLibrary("cbit location?", Arrays.asList("gandipet", "b", "c"), "low", "collage",
								"gandipet")));
		quizList = new ArrayList<>();
		quizList.add(quiz1);
	}

	@Test
	void createQuizTest() throws Exception {

		List<Integer> questionIds = Arrays.asList(1);
		Mockito.when(quizService.addQuiz(quiz1Dto, questionIds)).thenReturn(quiz1);
		mockMvc.perform(post("/createQuiz").param("questionIds", "questionIds"))
				.andExpect(view().name("quizSucess.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "quiz created succesfully")).andReturn();
	}

	@Test
	void viewAllQuizTest() throws Exception {
		Mockito.when(quizService.viewQuiz()).thenReturn(quizList);
		mockMvc.perform(get("/viewAllQuiz")).andExpect(view().name("viewAllQuiz.jsp"))
				.andExpect(model().attributeExists("quizList"))
				.andExpect(model().attribute("quizList", quizService.viewQuiz())).andReturn();
	}

	@Test
	void viewQuizByIdTest() throws Exception {
		int id = 1;
		Mockito.when(quizService.viewByid(id)).thenReturn(quiz1);
		mockMvc.perform(get("/viewQuiz").param("id", "1")).andExpect(view().name("updateQuiz.jsp"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("quiz"))
				.andExpect(model().attribute("quiz", quizService.viewByid(id))).andReturn();
	}

	@Test
	void viewQuizByNotIdTest() throws Exception {
		int id = 1;
		Mockito.when(quizService.viewByid(id))
				.thenThrow(new QuizNotFoundException("quiz does not exist with given id"));
		mockMvc.perform(get("/viewQuiz").param("id", "1")).andExpect(view().name("exception.jsp"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("exception"))
				.andExpect(model().attribute("exception", "quiz does not exist with given id")).andReturn();
	}

	@Test
	void testDeleteExistingQuiz() throws Exception {
		Mockito.when(quizService.deleteQuiz(1)).thenReturn(quiz1);
		mockMvc.perform(get("/deletequiz").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("quizSucess.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "quiz deleted  succesfully")).andReturn();
	}

	@Test
	void testDeleteUnExistingQuiz() throws Exception {
		Mockito.when(quizService.deleteQuiz(100))
				.thenThrow(new QuizNotFoundException("quiz does not exist with given id"));
		mockMvc.perform(get("/deletequiz").param("id", "100")).andExpect(status().isOk())
				.andExpect(view().name("exception.jsp")).andExpect(model().attributeExists("exception"))
				.andExpect(model().attribute("exception", "quiz does not exist with given id")).andReturn();
	}

	@Test
	void updateQuizTest() throws Exception {

		Mockito.when(quizService.editQuiz(quiz1Dto,Arrays.asList(1))).thenReturn(quiz1);
		mockMvc.perform(post("/updateQuiz")).andExpect(view().name("quizSucess.jsp")).andExpect(status().isOk())
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "updated quiz succesfully")).andReturn();
	}

}
