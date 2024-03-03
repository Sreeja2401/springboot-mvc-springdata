package com.epam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.AdminAndUser;

public interface AdminAndUserRepository extends CrudRepository<AdminAndUser ,Integer> {
   public Optional<AdminAndUser> findByuserName(String userName);
}
