package com.epam.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.customexceptions.DuplicateQuestionFoundException;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.dto.QuestionsLibraryDto;
import com.epam.entity.QuestionsLibrary;
import com.epam.repository.QuestionsLibraryRepository;
import com.epam.service.QuestionsLibraryService;

@ExtendWith(MockitoExtension.class)
public class QuestionLibraryServiceTest {

	@Mock
	private QuestionsLibraryRepository questionsRepo;
	@Mock
	private ModelMapper mapper;

	@InjectMocks
	private QuestionsLibraryService questionsLibraryService;

	QuestionsLibrary question;
	QuestionsLibraryDto questionDto;
	List<QuestionsLibrary> questionList;
	List<QuestionsLibraryDto> questionDtoList;

	@BeforeEach
	public void setUp() {
		questionDto = new QuestionsLibraryDto("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium",
				"java", "8");
		question = new QuestionsLibrary("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java",
				"8");
		questionList = new ArrayList<>(Arrays.asList(question));
		questionDtoList = new ArrayList<>(Arrays.asList(questionDto));
	}

	@Test
	void createQuestionTest() throws DuplicateQuestionFoundException {
		Mockito.when(questionsRepo.findByquestionTitle(question.getQuestionTitle())).thenReturn(Optional.empty());
		Mockito.when(questionsRepo.save(question)).thenReturn(question);
		QuestionsLibrary savedQuestion = questionsLibraryService.save(questionDto);
		assertEquals(question, savedQuestion);
	}

	@Test
	void createQuestionWithExistingTitle() throws DuplicateQuestionFoundException {
		 Mockito.when(mapper.map(questionDto,QuestionsLibrary.class)).thenReturn(question);
		Mockito.when(questionsRepo.findByquestionTitle(question.getQuestionTitle())).thenReturn(Optional.of(question));
		assertThrows(DuplicateQuestionFoundException.class, () -> questionsLibraryService.save(questionDto));
		Mockito.verify(questionsRepo).findByquestionTitle(question.getQuestionTitle());
	}

	@Test
	void deleteQuestionTest() throws QuestionIdNotFoundException {
		Mockito.when(questionsRepo.findById(1)).thenReturn(Optional.of(question));
		doNothing().when(questionsRepo).delete(question);
		questionsRepo.delete(question);
		verify(questionsRepo).delete(question);
		QuestionsLibrary deletedQuestion = questionsLibraryService.delete(1);
		assertEquals(deletedQuestion, question);
	}

	@Test
	void deleteQuestionWithNonExistingIdTest() throws QuestionIdNotFoundException {
		Mockito.when(questionsRepo.findById(1)).thenReturn(Optional.empty());
		doNothing().when(questionsRepo).delete(question);
		questionsRepo.delete(question);
		verify(questionsRepo).delete(question);
		assertThrows(QuestionIdNotFoundException.class, () -> questionsLibraryService.delete(1));
	}

	@Test
	void viewQuestionByIdTest() throws QuestionIdNotFoundException {
		question.setQuestionNumber(1);
		Mockito.when(questionsRepo.findById(1)).thenReturn(Optional.of(question));
		QuestionsLibrary retrievedQuestion = questionsLibraryService.viewById(1);
		Mockito.verify(questionsRepo).findById(1);
		assertEquals(question, retrievedQuestion);
	}

	@Test
	void viewQuestionWithoutIdPresentTest() {
		Mockito.when(questionsRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(QuestionIdNotFoundException.class, () -> questionsLibraryService.viewById(1));
		Mockito.verify(questionsRepo).findById(1);
	}

	@Test
	void viewAllQuestions() {
		Mockito.when(questionsRepo.findAll()).thenReturn(questionList);
		List<QuestionsLibrary> retrievedQuestionsList = questionsLibraryService.viewAll();
		Mockito.verify(questionsRepo).findAll();
		assertEquals(questionList, retrievedQuestionsList);

	}

	@Test
	void updateQuestion() throws QuestionIdNotFoundException, DuplicateQuestionFoundException  {
		question.setQuestionNumber(1);
		Mockito.when(questionsRepo.findById(question.getQuestionNumber())).thenReturn(Optional.of(question));
		Mockito.when(questionsRepo.save(question)).thenReturn(question);
		QuestionsLibrary updatedQuestion = questionsLibraryService.updateQuestion(question);
		assertEquals(updatedQuestion, question);
	}
	@Test
	void updateQuestionWithNonExistingIdTest() throws QuestionIdNotFoundException {
		Mockito.when(questionsRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(QuestionIdNotFoundException.class, () -> questionsLibraryService.delete(1));
	}
	

}
