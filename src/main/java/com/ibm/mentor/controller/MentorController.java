package com.ibm.mentor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mentor.model.Mentor;
import com.ibm.mentor.repository.MentorRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mentor")
public class MentorController {

	@Autowired
	MentorRepository mentorRepository;	
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getMentor(@PathVariable String username) {
		Mentor mentor = mentorRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Mentor not found."));

		return new ResponseEntity<>(mentor, HttpStatus.OK);
	}
}
