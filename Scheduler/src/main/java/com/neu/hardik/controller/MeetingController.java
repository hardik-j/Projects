package com.neu.hardik.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.hardik.dao.MeetingDAO;
import com.neu.hardik.dao.TaskDAO;
import com.neu.hardik.pojo.Meeting;
import com.neu.hardik.pojo.Task;
import com.neu.hardik.pojo.User;

@Controller
@RequestMapping(value = "/meeting")
public class MeetingController {
	private static final Logger LOGGER = Logger.getLogger(MeetingController.class);

	@Autowired
	private TaskDAO taskDao;

	@Autowired
	private MeetingDAO meetingDao;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("team", user.getTeam());
		return "create-meeting";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=*/*")
	public void addMeeting(@RequestBody Meeting meeting) {
		LOGGER.info("Adding meeting!");
		for (Task task : meeting.getTasks()) {
			Task addTask = taskDao.addTask(task);
			sendEmail(addTask);
		}
		meetingDao.addMeeting(meeting);
	}

	private void sendEmail(Task task) {
		// TODO Auto-generated method stub
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu");
			email.setSubject("Password Reminder");
			email.setSubject("New task mail!!");
			email.setMsg("Hi " + task.getUser().getFirstName() + ",\n A new task has been assigned to you!!\n "
					+ task.getDescription());
			email.addTo(task.getUser().getEmail());
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}

	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getMeetings(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("meetingList", meetingDao.getMeetings(user.getTeam()));
		return "team-meetings";
	}

}
