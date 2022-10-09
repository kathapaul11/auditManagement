package com.cognizant.authorization;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.authorization.controller.AuthorizationController;
import com.cognizant.authorization.exception.UserNotFoundException;
import com.cognizant.authorization.model.MyUser;
import com.cognizant.authorization.model.UserCredentials;
import com.cognizant.authorization.repo.MyUserRepo;
import com.cognizant.authorization.service.UserDetailServiceImpl;
import com.cognizant.authorization.util.JwtUtil;


@SpringBootTest
@RunWith(SpringRunner.class)
class AuthorizationServiceApplicationTests {

	@InjectMocks
	AuthorizationController authController;
	
	UserDetails userDetails; 

	@Mock
	JwtUtil jwtutil;

	@Mock
	UserDetailServiceImpl custdetailservice;

	@Mock
	MyUserRepo userservice;

	@Test
	public void validLoginTest() {

		UserCredentials user = new UserCredentials("admin", "admin");
		UserDetails value = new User(user.getUserName(), user.getPassword(), new ArrayList<>());
		when(custdetailservice.loadUserByUsername("admin")).thenReturn(value);
		when(jwtutil.generateToken(user.getUserName())).thenReturn("token");
		ResponseEntity<?> login = authController.login(user);
		assertEquals(200,login.getStatusCodeValue());
		
	}



	@Test
	public void userNameNotFoundLoginTest() {
		UserCredentials user = new UserCredentials("123", "abc");
		Exception exception = assertThrows(UserNotFoundException.class, () -> {
			authController.login(user);
		});

		String expectedMessage = "Invalid Credential";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void validateTestValidtoken() {

		UserCredentials user = new UserCredentials("admin", "admin");
		UserDetails value = new User(user.getUserName(), "admin", new ArrayList<>());

		when(jwtutil.validateToken("token", value)).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("admin");

		MyUser user1 = new MyUser(1, "admin", "admin");
		Optional<MyUser> data = Optional.of(user1);

		when(userservice.findById(1)).thenReturn(data);

		ResponseEntity<?> validity = authController.validate("Bearer token");

		assertEquals(false, validity.getBody().toString().contains("true"));

	}

	@Test
	public void validateTestInValidUsertoken() {

		UserCredentials user = new UserCredentials("admin", "admin");
		UserDetails value = new User(user.getUserName(), "admin", new ArrayList<>());

		when(jwtutil.validateToken("token1", value)).thenReturn(false);

		ResponseEntity<?> validity = authController.validate("Bearer token1");

		assertEquals(true,validity.getBody().toString().contains("false"));

	}

	@Test
	public void validateTestInValidtoken() {

		UserCredentials user = new UserCredentials("admin", "admin");
		UserDetails value = new User(user.getUserName(), "admin", new ArrayList<>());

		when(jwtutil.validateToken("token", value)).thenReturn(false);

		ResponseEntity<?> validity = authController.validate("bearer token");

		assertEquals(true, validity.getBody().toString().contains("false"));

	}

	
}