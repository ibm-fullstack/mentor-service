package com.ibm.mentor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.mentor.message.request.ProposeRequest;
import com.ibm.mentor.message.request.SearchForm;
import com.ibm.mentor.message.request.UpdateStatusRequest;
import com.ibm.mentor.message.response.ResponseMessage;
import com.ibm.mentor.model.Mentor;
import com.ibm.mentor.model.TrainingStatus;
import com.ibm.mentor.model.Trainings;
import com.ibm.mentor.model.User;
import com.ibm.mentor.repository.MentorRepository;
import com.ibm.mentor.repository.SkillsRepository;
import com.ibm.mentor.repository.TrainingRepository;
import com.ibm.mentor.repository.UserRepository;
import com.ibm.mentor.sendingmail.mail.MailSender;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/training")
public class TrainingController {

	@Autowired
	TrainingRepository trainingRepository;	
	
	@Autowired
	MentorRepository mentorRepository;	
	
	@Autowired
	UserRepository userRepository;	
	
	@Autowired
	SkillsRepository skillsRepository;	
	
	@Autowired
	@Qualifier("mentorMailSender")
	public MailSender mailSender;
	
	@PostMapping("/propose")
	public ResponseEntity<?> propose(@RequestBody ProposeRequest proposeRequest) {

		Trainings training = new Trainings();
		Mentor mentor = mentorRepository.getOne(proposeRequest.getMentorId());
		User user = userRepository.getOne(proposeRequest.getUserId());
		training.setMentor(mentor);
		training.setUser(user);
		training.setSkills(skillsRepository.getOne(proposeRequest.getSkillId()));
		training.setProgress(0);
		training.setStatus(TrainingStatus.PROPOSED);
		
		trainingRepository.save(training);
		
		String from = "mentorservice22@gmail.com";
		String to = mentor.getLinkedin();
		String subject = "You have a pending training proposal";
		String body = "User " + user.getName() + " has sent you a training proposal! Login to the app to confirm.";
		
		mailSender.sendMail(from, to, subject, body);

		return new ResponseEntity<>(new ResponseMessage("Training Proposal Sent!"), HttpStatus.OK);
	}
	
	@GetMapping("/view/mentor/{mentorid}")
	public ResponseEntity<?> viewTrainingsForMentor(@PathVariable String mentorid) {

		long id = Long.parseLong(mentorid);
		List<Trainings> trainingList = trainingRepository.findByMentorId(id);

		return new ResponseEntity<>(trainingList, HttpStatus.OK);
	}
	
	@GetMapping("/view/user/{userid}")
	public ResponseEntity<?> viewTrainingsForUser(@PathVariable String userid) {

		long id = Long.parseLong(userid);
		List<Trainings> trainingList = trainingRepository.findByUserId(id);

		return new ResponseEntity<>(trainingList, HttpStatus.OK);
	}	
	
	@PostMapping("/updatestatus")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateStatusRequest updateStatusRequest) {

		Trainings training = trainingRepository.getOne(updateStatusRequest.getId());
		training.setStatus(TrainingStatus.valueOf(updateStatusRequest.getStatus()));
		
		trainingRepository.save(training);

		return new ResponseEntity<>(new ResponseMessage("Training Status Updated!"), HttpStatus.OK);
	}
}
