package com.ibm.mentor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.mentor.message.request.SearchForm;
import com.ibm.mentor.message.response.SearchResult;
import com.ibm.mentor.model.Mentor;
import com.ibm.mentor.model.Role;
import com.ibm.mentor.model.RoleName;
import com.ibm.mentor.model.Skills;
import com.ibm.mentor.model.User;
import com.ibm.mentor.repository.MentorRepository;
import com.ibm.mentor.repository.SkillsRepository;
import com.ibm.mentor.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	MentorRepository mentorRepository;	
	
	@Autowired
	SkillsRepository skillsRepository;	
	
	@Autowired
	UserRepository userRepository;	
	
	@PostMapping("/skill")
	public ResponseEntity<?> searchSkill(@Valid @RequestBody SearchForm searchRequest) throws JsonProcessingException {

		User user = null;
		List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		List<Mentor> list = mentorRepository.findBySkillsNameContainingAndStartTimeLessThanAndEndTimeGreaterThan(searchRequest.getSkill(), searchRequest.getStartTime(), searchRequest.getEndTime());		
		
		Skills skill = skillsRepository.findByName(searchRequest.getSkill())
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Skill not found."));
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
		  String username = ((UserDetails)principal).getUsername();
		  user = userRepository.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));	  
		} 
		
		for (Mentor mentor : list) {
			SearchResult searchResult = new SearchResult();
			searchResult.setMentor(mentor);
			searchResult.setSkillId(skill.getId());
			searchResult.setUserId(user == null ? 0 : user.getId());
			searchResultList.add(searchResult);
		}
	
		return new ResponseEntity<>(searchResultList, HttpStatus.OK);
	}
}
