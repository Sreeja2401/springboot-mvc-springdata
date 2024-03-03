package com.epam.servicetest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.epam.customexceptions.DuplicateQuizFoundException;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.customexceptions.QuizNotFoundException;
import com.epam.dto.QuizLibraryDto;
import com.epam.entity.QuestionsLibrary;
import com.epam.entity.QuizLibrary;
import com.epam.repository.QuestionsLibraryRepository;
import com.epam.repository.QuizLibraryRepository;
import com.epam.service.QuizLibraryService;

@ExtendWith(MockitoExtension.class)
public class QuizLibraryServiceTest {
		@Mock
		private QuizLibraryRepository quizRepository;
		@Mock
		private ModelMapper modelMapper;
		@Mock
		private QuestionsLibraryRepository questionRepository;
		
		@InjectMocks
		private QuizLibraryService quizLibraryService;
		List<QuizLibrary> quizList;
		QuizLibrary quiz1;
		QuizLibrary quiz2;

		@BeforeEach
		public void setUp() {
			quiz1 = new QuizLibrary("colleges", 65,
					Arrays.asList(
							new QuestionsLibrary("vjit location", Arrays.asList("a", "b", "c"), "low", "collage", "a"),
							new QuestionsLibrary("cbit location?", Arrays.asList("gandipet", "b", "c"), "low", "collage",
									"gandipet")));
			quiz2 = new QuizLibrary("vjit", 65, Arrays
					.asList(new QuestionsLibrary("vjit location", Arrays.asList("a", "b", "c"), "low", "collage", "a")));
			quizList = new ArrayList<>();
			quizList.add(quiz1);
			quizList.add(quiz2);
		}
		
		@Test
		void addQuiz() throws DuplicateQuizFoundException {
			QuizLibraryDto quiz3Dto = new QuizLibraryDto(1,"cbit", 65, Arrays
					.asList(new QuestionsLibrary("vjit location", Arrays.asList("a", "b", "c"), "low", "collage", "a")));
			QuizLibrary quiz3 = new QuizLibrary("cbit", 65, Arrays
					.asList(new QuestionsLibrary("vjit location", Arrays.asList("a", "b", "c"), "low", "collage", "a")));
			Mockito.when(quizRepository.findBytitle(quiz3.getTitle())).thenReturn(Optional.empty());
			Mockito.when(quizRepository.save(quiz3)).thenReturn(quiz3);
			QuizLibrary retrievedQuiz = quizLibraryService.addQuiz(quiz3Dto, Arrays.asList(1,2));
			assertEquals(retrievedQuiz, quiz3);
		}

		@Test
		void addQuizWithDuplicateTitle() throws DuplicateQuizFoundException {
			QuizLibraryDto quiz3Dto = new QuizLibraryDto(1,"cbit", 65, Arrays
					.asList(new QuestionsLibrary("vjit location", Arrays.asList("a", "b", "c"), "low", "collage", "a")));
			QuizLibrary quiz3 = new QuizLibrary("cbit", 65, Arrays
					.asList(new QuestionsLibrary("vjit location", Arrays.asList("a", "b", "c"), "low", "collage", "a")));
			Mockito.when(quizRepository.findBytitle(quiz1.getTitle())).thenReturn(Optional.of(quiz1));
			assertThrows(DuplicateQuizFoundException.class, () -> quizLibraryService.addQuiz(quiz3Dto, Arrays.asList(1,2)));
		}

		


		@Test
		void viewAllQuizTest() {
			Mockito.when(quizRepository.findAll()).thenReturn(quizList);
			List<QuizLibrary> retrievedQuizList = quizLibraryService.viewQuiz();
			assertEquals(quizList, retrievedQuizList);
		}

		@Test
		void viewQuizByExistingIdTest() throws QuizNotFoundException {
			int id = 1;
			Mockito.when(quizRepository.findById(id)).thenReturn(Optional.of(quiz1));
			QuizLibrary retrievedQuiz = quizLibraryService.viewByid(id);
			assertNotNull(retrievedQuiz);
		}

		@Test
		void viewQuizByNotExistingIdTest() throws QuizNotFoundException {
			int id = 1;
			Mockito.when(quizRepository.findById(id)).thenReturn(Optional.empty());
			assertThrows(QuizNotFoundException.class, () -> quizLibraryService.viewByid(id));

		}

		@Test
		void deleteQuizWithExistingId() throws QuizNotFoundException {
			int id = 1;
			Mockito.when(quizRepository.findById(id)).thenReturn(Optional.of(quiz1));
			doNothing().when(quizRepository).delete(quiz1);;
			quizRepository.delete(quiz1);
			verify(quizRepository).delete(quiz1);
			QuizLibrary deletedQuiz = quizLibraryService.deleteQuiz(id);
			assertEquals(deletedQuiz, quiz1);
		}

		@Test
		void deleteQuizWithNonExistingId() throws QuizNotFoundException {
			int id = 1;
			Mockito.when(quizRepository.findById(1)).thenReturn(Optional.empty());

			assertThrows(QuizNotFoundException.class, () -> quizLibraryService.deleteQuiz(id));
		}

	}
		
		
		
		
		
		
		
		
		
