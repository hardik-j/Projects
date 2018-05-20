package com.neu.hardik.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.hardik.dao.UserDAO;
import com.neu.hardik.pojo.User;
import com.neu.hardik.utility.Constants;
import com.neu.hardik.utility.Constants.Role;

@Controller
@RequestMapping("/utility")
public class UtilityController {

	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "/teams", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody String getTeams() {
		return Arrays.asList(Constants.TEAMS).toString();
	}

	@RequestMapping(value = "/team/{teamName}", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody List<User> getTeamUsers(@PathVariable String teamName, Model model) {
		List<User> teamMembers = userDao.getTeamMembers(teamName);
		System.out.println(teamMembers);
		return teamMembers;
	}

	@RequestMapping(value = "/role/")
	@ResponseBody
	public List<Role> roles() {
		return Arrays.asList(Role.values());
	}
}
