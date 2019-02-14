package com.ibm.mentor.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "trainings")
public class Trainings {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "mentor_id")
	private Mentor mentor;
	
	@ManyToOne
    @JoinColumn(name = "skills_id")
	private Skills skills;
	
	@Enumerated
	private TrainingStatus status;
	
	private int progress;

    public Trainings() {}

//    public Trainings(String username, String linkedin, int yearsExp, int startTime, int endTime) {
//        this.username = username;
//        this.linkedin = linkedin;
//        this.yearsExp = yearsExp;
//        this.startTime = startTime;
//        this.endTime = endTime;		
//    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public Skills getSkills() {
		return skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
	}

	public TrainingStatus getStatus() {
		return status;
	}

	public void setStatus(TrainingStatus status) {
		this.status = status;
	}

}
