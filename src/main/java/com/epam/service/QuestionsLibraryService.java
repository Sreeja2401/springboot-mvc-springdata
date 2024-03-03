package com.epam.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.DuplicateQuestionFoundException;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.dto.QuestionsLibraryDto;
import com.epam.entity.QuestionsLibrary;
import com.epam.repository.QuestionsLibraryRepository;

@Service
public class QuestionsLibraryService {
	ModelMapper mapper = new ModelMapper();

	@Autowired
	QuestionsLibraryRepository questionsRepo;

	public QuestionsLibrary save(QuestionsLibraryDto questionDto) throws DuplicateQuestionFoundException {
		Optional<QuestionsLibrary> questionOptional = questionsRepo.findByquestionTitle(questionDto.getQuestionTitle());
		QuestionsLibrary question = mapper.map(questionDto, QuestionsLibrary.class);
		if (questionOptional.isEmpty()) {
			questionsRepo.save(question);
			return question;
		} else {
			throw new DuplicateQuestionFoundException("question with given title already exist");
		}
	}

	public QuestionsLibrary delete(int questionNumber) throws QuestionIdNotFoundException {
		return questionsRepo.findById(questionNumber).map(q -> {
			questionsRepo.delete(q);
			return q;
		}).orElseThrow(() -> new QuestionIdNotFoundException("id not found"));
	}

	public List<QuestionsLibrary> viewAll() {
		return (List<QuestionsLibrary>) questionsRepo.findAll();
	}

	public QuestionsLibrary viewById(int id) throws QuestionIdNotFoundException {
		return questionsRepo.findById(id).orElseThrow(() -> new QuestionIdNotFoundException("id not found"));
	}

	public QuestionsLibrary updateQuestion(QuestionsLibrary question) throws QuestionIdNotFoundException {
		
		/*
		 * ModelMapper mapper = new ModelMapper(); QuestionsLibrary question =
		 * mapper.map(questionDto, QuestionsLibrary.class);
		 */
		return questionsRepo.findById(question.getQuestionNumber()).map(q -> {
			return questionsRepo.save(question);
		}).orElseThrow(() -> new QuestionIdNotFoundException("id not found"));
	}

}
