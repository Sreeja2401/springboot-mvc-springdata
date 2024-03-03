package com.epam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.QuestionsLibrary;

public interface QuestionsLibraryRepository extends CrudRepository<QuestionsLibrary ,Integer> {
	public Optional<QuestionsLibrary> findByquestionTitle(String questionTitle);
}
