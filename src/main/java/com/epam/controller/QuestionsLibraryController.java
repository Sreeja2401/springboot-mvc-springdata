package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexceptions.DuplicateQuestionFoundException;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.dto.QuestionsLibraryDto;
import com.epam.entity.QuestionsLibrary;
import com.epam.service.QuestionsLibraryService;

@Controller
public class QuestionsLibraryController {

	@Autowired
	private QuestionsLibraryService questionOperation;

	public static final String MESSAGE = "message";
	public static final String SUCCESS = "questionSucess.jsp";
	public static final String EXCEPTION = "exception.jsp";

	@PostMapping("addQuestions")
	public ModelAndView createQuestion(QuestionsLibraryDto questionDto) throws DuplicateQuestionFoundException {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(questionDto.toString());
		try {
			if (questionOperation.save(questionDto) != null) {
				modelAndView.addObject(MESSAGE, "question created  succesfully");
				modelAndView.setViewName(SUCCESS);
			}
		} catch (DuplicateQuestionFoundException e) {

			modelAndView.addObject("exception", "question with given title already exist");
			modelAndView.setViewName(EXCEPTION);
		}
		return modelAndView;
	}

	@GetMapping("viewAllQuestions")
	public ModelAndView viewAllQuestions() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("questions", questionOperation.viewAll());
		modelAndView.setViewName("viewAllQuestions.jsp");
		return modelAndView;
	}

	@GetMapping("viewQuestion")
	public ModelAndView viewQuestionById(@RequestParam("questionNumber") int questionNumber) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("question", questionOperation.viewById(questionNumber));
			modelAndView.setViewName("updateQuestion.jsp");
		} catch (QuestionIdNotFoundException e) {
			modelAndView.addObject("exception", e.getMessage());
			modelAndView.setViewName(EXCEPTION);
		}
		return modelAndView;
	}

	@GetMapping("deleteQuestion")
	public ModelAndView deleteQuestion(@RequestParam("questionNumber") int questionNumber)
			throws QuestionIdNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (questionOperation.delete(questionNumber) != null) {
				modelAndView.addObject(MESSAGE, "question deleted  succesfully");
				modelAndView.setViewName(SUCCESS);
			}

		} catch (QuestionIdNotFoundException e) {
			modelAndView.addObject("exception", e.getMessage());
			modelAndView.setViewName(EXCEPTION);
		}
		return modelAndView;

	}

	@PostMapping("updateQuestion")
	public ModelAndView updateQuestion(QuestionsLibrary modifiedQuestion) throws QuestionIdNotFoundException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("question", questionOperation.updateQuestion(modifiedQuestion));
		mv.addObject(MESSAGE, "updated question successfully");
		mv.setViewName(SUCCESS);
		return mv;

	}

}
