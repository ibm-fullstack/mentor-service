package com.ibm.mentor.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SearchForm {
    @NotBlank
    private String skill;

    private int startTime;

    private int endTime;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

}