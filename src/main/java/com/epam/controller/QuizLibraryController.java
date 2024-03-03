package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexceptions.DuplicateQuizFoundException;
import com.epam.customexceptions.QuizNotFoundException;
import com.epam.dto.QuizLibraryDto;
import com.epam.entity.QuizLibrary;
import com.epam.service.QuestionsLibraryService;
import com.epam.service.QuizLibraryService;

@Controller
public class QuizLibraryController {

	@Autowired
	private QuizLibraryService quizOperations;
	@Autowired
	private QuestionsLibraryService questionsService;

	public static final String MESSAGE = "message";
	public static final String SUCCESS = "questionSucess.jsp";
	public static final String EXCEPTION = "exception.jsp";

	@PostMapping("createQuiz")
	public ModelAndView createQuiz(QuizLibraryDto quiz, @RequestParam(value = "questionIds") List<Integer> questionIds) {

		ModelAndView modelAndView = new ModelAndView("quizSucess.jsp");
		try {
			QuizLibrary savedQuiz = quizOperations.addQuiz(quiz, questionIds);
			modelAndView.addObject(MESSAGE, "quiz created succesfully");
		} catch (DuplicateQuizFoundException e) {
			modelAndView.addObject(MESSAGE, e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping("viewAllQuiz")
	public ModelAndView viewAllQuiz() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("quizList", quizOperations.viewQuiz());
		modelAndView.addObject("questionList", questionsService.viewAll());
		modelAndView.setViewName("viewAllQuiz.jsp");
		return modelAndView;
	}

	@GetMapping("viewQuiz")
	public ModelAndView viewQuizById(int id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("quiz", quizOperations.viewByid(id));
			modelAndView.addObject("questionList",questionsService.viewAll());
			modelAndView.setViewName("updateQuiz.jsp");
			
		} catch (QuizNotFoundException e) {
			modelAndView.addObject("exception", e.getMessage());
			modelAndView.setViewName(EXCEPTION);
		}
		return modelAndView;
	}

	@GetMapping("deletequiz")
	public ModelAndView deleteQuiz(@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (quizOperations.deleteQuiz(id) != null) {
				modelAndView.addObject(MESSAGE, "quiz deleted  succesfully");
				modelAndView.setViewName("quizSucess.jsp");
			}
		} catch (QuizNotFoundException e) {
			modelAndView.addObject("exception", e.getMessage());
			modelAndView.setViewName(EXCEPTION);
		}
		return modelAndView;

	}
	@PostMapping("updateQuiz")
	public ModelAndView updateQuiz(QuizLibraryDto quiz,@RequestParam("questionIds") List<Integer> questionIds) {
		ModelAndView modelAndView = new ModelAndView();		
			if (quizOperations.editQuiz(quiz,questionIds) != null) {
				modelAndView.addObject(MESSAGE, "updated quiz succesfully");
				modelAndView.setViewName("quizSucess.jsp");
			}
		return modelAndView;

	}
	@GetMapping("addQuestionsToQuiz")
	public ModelAndView addQuestionsToQuiz()
	{
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("questionList",questionsService.viewAll());
		modelAndView.setViewName("createQuiz.jsp");
		return modelAndView;
		
	}
	
	
	
	
	
	
	
	
	
	
}
