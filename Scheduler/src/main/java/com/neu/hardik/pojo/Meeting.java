package com.neu.hardik.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long meetingId;
	private String agenda;

	@OneToMany
	@JoinColumn(name = "meeting_id")
	private List<Task> tasks;
	private String team;

	public Meeting() {
		tasks = new ArrayList<>();
	}

	public long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(long meetingId) {
		this.meetingId = meetingId;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
}
