package com.neu.hardik.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.hardik.dao.UserDAO;
import com.neu.hardik.pojo.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, HttpServletRequest request) {
		User savedUser = userDao.addUser(user);
		if (savedUser == null) {
			LOGGER.debug("Could not create user(Username already exists or something is wrong with the connection) "
					+ user.getUsername());
			return "login";
		}
		LOGGER.debug("User created with name : " + savedUser.getUsername());
		HttpSession session = request.getSession();
		session.setAttribute("user", savedUser);
		return "dashboard";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String removeUser(@PathVariable int id) {
		return "dashboard";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable int id) {
		return "user-profile";
	}


}
