package com.ibm.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ibm.mentor.sendingmail.mail.MailSender;

@SpringBootApplication
public class SpringBootMentorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMentorApplication.class, args);
	}
}
