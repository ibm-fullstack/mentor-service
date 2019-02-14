package com.ibm.mentor.message.request;

public class ProposeRequest {

	private Long userId;
	private Long skillId;
	private Long mentorId;
	
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
	public Long getMentorId() {
		return mentorId;
	}
	public void setMentorId(Long mentorId) {
		this.mentorId = mentorId;
	}
}
