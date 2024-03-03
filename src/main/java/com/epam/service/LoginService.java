package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.AdminAndUserDto;
import com.epam.entity.AdminAndUser;
import com.epam.repository.AdminAndUserRepository;

@Service
public class LoginService {

	@Autowired
	AdminAndUserRepository adminAndUserRepo;

	public boolean findMatchingUser(AdminAndUserDto roleDto) {

		ModelMapper mapper = new ModelMapper();
		AdminAndUser role = mapper.map(roleDto, AdminAndUser.class);

		return adminAndUserRepo.findByuserName(role.getUserName()).map(
				user -> user.getUserName().equals(role.getUserName()) && user.getPassword().equals(role.getPassword()))
				.orElse(false);
	}

	public boolean saveUser(AdminAndUserDto userDto) {

		boolean result = false;

		ModelMapper mapper = new ModelMapper();
		AdminAndUser user = mapper.map(userDto, AdminAndUser.class);

		if (adminAndUserRepo.findByuserName(user.getUserName()).isEmpty()) {
			adminAndUserRepo.save(user);
			result = true;
		}
		return result;
		
	}

}
