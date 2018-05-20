package com.neu.hardik.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.captcha.botdetect.web.servlet.Captcha;
import com.neu.hardik.dao.UserDAO;
import com.neu.hardik.pojo.User;
import com.neu.hardik.utility.Constants;
import com.neu.hardik.utility.Constants.Role;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private UserDAO userDao;
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null)
			return "login";
		else 
			return "redirect:/task/mytask";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(HttpServletRequest request) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		User u = userDao.getUser(user, false);
		if (u != null) {
			request.getSession().setAttribute("user", u);
			return "redirect:/task/mytask";
		}
		return "login";
	}

	@RequestMapping(value = "/register")
	public String goToRegister(Model model) {
		List<String> roles = new ArrayList<>();
		for (Role role : Role.values())
			roles.add(role.toString());
		model.addAttribute("roles", roles);
		model.addAttribute("teams", Arrays.asList(Constants.TEAMS));
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user, HttpServletRequest request) {
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		if (captcha.validate(captchaCode)) {
			User addUser = userDao.addUser(user);
			LOGGER.info("Added user with id : " + addUser.getUserId());
			return "redirect:/task/mytask";
		}
		return "redirect:/login/register";
	}
}
