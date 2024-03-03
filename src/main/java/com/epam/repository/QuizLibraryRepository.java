package com.epam.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.epam.entity.QuizLibrary;


public interface QuizLibraryRepository extends CrudRepository<QuizLibrary ,Integer> {
	public Optional<QuizLibrary> findBytitle(String title);
}

