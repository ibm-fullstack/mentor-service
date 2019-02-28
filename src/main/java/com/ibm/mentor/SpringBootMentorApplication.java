package com.ibm.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ibm.mentor.sendingmail.mail.MailSender;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SpringBootMentorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMentorApplication.class, args);
	}
}
