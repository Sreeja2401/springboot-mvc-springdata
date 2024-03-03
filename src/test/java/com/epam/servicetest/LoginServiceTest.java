package com.epam.servicetest;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.AdminAndUserDto;
import com.epam.entity.AdminAndUser;
import com.epam.repository.AdminAndUserRepository;
import com.epam.service.LoginService;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

	    @Mock
	    AdminAndUserRepository adminAndUserRepo;
	    
	    @InjectMocks
	    LoginService loginService;
	   
	   AdminAndUser admin;
	   AdminAndUser user;
	   AdminAndUserDto adminDto;
	   AdminAndUserDto userDto;
	   
	   @BeforeEach
       public void setData()
      {
           admin=new AdminAndUser("admin","pinky","123");
           user=new AdminAndUser("user","sree","abc");
           adminDto=new AdminAndUserDto("admin","pinky","123");
           userDto=new AdminAndUserDto("user","sree","abc");
      }   
	    @Test
	     void validateAdminTest()
	    {
	    	
	        Mockito.when(adminAndUserRepo.findByuserName(admin.getUserName())).thenReturn(Optional.of(admin));
	        boolean result =loginService.findMatchingUser(adminDto);
	        assertTrue(result);
	    }
	    @Test
	     void validateAdminWithInvalidCredentials()
	    {
	    	Mockito.when(adminAndUserRepo.findByuserName(admin.getUserName())).thenReturn(Optional.empty());
	        boolean result =loginService.findMatchingUser(adminDto);
	        assertFalse(result);
	    }@Test
	     void userSignUpTest()
	    {
	        Mockito.when(adminAndUserRepo.save(user)).thenReturn(user);
	        boolean result =loginService.saveUser(userDto);
	        assertTrue(result);
	    }
	    @Test
	     void userSignInWithNotExistingUser()
	    {
	        AdminAndUser user=new AdminAndUser("user","pandu","1234");
	        Mockito.when(adminAndUserRepo.findByuserName( userDto.getUserName())).thenReturn(Optional.empty());
	        boolean result =loginService.findMatchingUser(userDto);
	        assertFalse(result);
	    }
	    @Test
	     void userSignInWithExistingUser()
	     {

	        AdminAndUser user=new AdminAndUser("user","pandu","1234");
	        Mockito.when(adminAndUserRepo.findByuserName( userDto.getUserName())).thenReturn(Optional.of(user));
	        boolean result =loginService.findMatchingUser(userDto);
	        assertTrue(result);




	}

}
