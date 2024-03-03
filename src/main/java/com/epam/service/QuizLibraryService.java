package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.DuplicateQuizFoundException;
import com.epam.customexceptions.QuizNotFoundException;
import com.epam.dto.QuizLibraryDto;
import com.epam.entity.QuestionsLibrary;
import com.epam.entity.QuizLibrary;
import com.epam.repository.QuestionsLibraryRepository;
import com.epam.repository.QuizLibraryRepository;

@Service
public class QuizLibraryService {
	@Autowired
	QuizLibraryRepository quizRepo;
	@Autowired
	QuestionsLibraryRepository questionRepo;

	public QuizLibrary addQuiz(QuizLibraryDto quizDto, List<Integer> questionIds) throws DuplicateQuizFoundException {
		
		  ModelMapper mapper = new ModelMapper(); 
		  QuizLibrary quiz = mapper.map(quizDto, QuizLibrary.class);
		 
		if (quizRepo.findBytitle(quiz.getTitle()).isEmpty()) {
			List<QuestionsLibrary> questionsList = questionIds.stream().distinct()
					.filter(id -> questionRepo.findById(id).isPresent()).map(id -> questionRepo.findById(id).get())
					.toList();
			quiz.setQuestionLibrary(questionsList);
			System.out.println(quiz);
			return quizRepo.save(quiz);

		} else {
			throw new DuplicateQuizFoundException("quiz with given title already exist");
		}

	}

	public QuizLibrary deleteQuiz(int id) throws QuizNotFoundException {
		return quizRepo.findById(id).map(q -> {
			quizRepo.delete(q);
			return q;
		}).orElseThrow(() -> new QuizNotFoundException("quiz does not exist with given id"));

	}

	public List<QuizLibrary> viewQuiz() {
		return (List<QuizLibrary>) quizRepo.findAll();
	}

	public QuizLibrary viewByid(int id) throws QuizNotFoundException {
		return quizRepo.findById(id).orElseThrow(() -> new QuizNotFoundException("quiz does not exist with given id"));
		
	}

	public QuizLibrary editQuiz(QuizLibraryDto quizDto,List<Integer> ids) {
		
		  ModelMapper mapper = new ModelMapper(); 
		  QuizLibrary quiz = mapper.map(quizDto, QuizLibrary.class);
		 quiz.setId(quizDto.getId());
		 System.out.println(quizDto);
		 List<QuestionsLibrary> questionList=ids.stream().filter(q->questionRepo.findById(q).isPresent()).map(q->questionRepo.findById(q).get()).toList();
		 quiz.setQuestionLibrary(questionList);
		 System.out.println(quiz);
		 return quizRepo.save(quiz);
		 

	}
	
}
