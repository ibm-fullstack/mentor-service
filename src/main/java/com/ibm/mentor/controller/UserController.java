package com.ibm.mentor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mentor.model.User;
import com.ibm.mentor.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;	
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getUser(@PathVariable String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
