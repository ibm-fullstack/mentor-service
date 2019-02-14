package com.ibm.mentor.message.response;

import com.ibm.mentor.model.Mentor;

public class SearchResult {

	private Long userId;
	private Long skillId;
	private Mentor mentor;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public Mentor getMentor() {
		return mentor;
	}
	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
	
	
}
