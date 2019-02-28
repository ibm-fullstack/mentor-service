package com.ibm.mentor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mentor.message.request.BlockUserRequest;
import com.ibm.mentor.message.request.UpdateRatingRequest;
import com.ibm.mentor.message.response.ResponseMessage;
import com.ibm.mentor.model.User;
import com.ibm.mentor.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;	
	
	@GetMapping
	public ResponseEntity<?> getUsers() {
		List<User> userList = userRepository.findAll();

		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getUser(@PathVariable String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/block")
	public ResponseEntity<?> blockUser(@RequestBody BlockUserRequest blockUserRequest) {
		User user = userRepository.findByUsername(blockUserRequest.getUsername()).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));

		user.setActive(false);
		userRepository.save(user);
		
		return new ResponseEntity<>(new ResponseMessage("User blocked!"), HttpStatus.OK);
	}
	
	@PostMapping("/unblock")
	public ResponseEntity<?> UnblockUser(@RequestBody BlockUserRequest blockUserRequest) {
		User user = userRepository.findByUsername(blockUserRequest.getUsername()).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));

		user.setActive(true);
		userRepository.save(user);
		
		return new ResponseEntity<>(new ResponseMessage("User Un-blocked!"), HttpStatus.OK);
	}
}
