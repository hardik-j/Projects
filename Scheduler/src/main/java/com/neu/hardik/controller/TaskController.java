package com.neu.hardik.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neu.hardik.dao.TaskDAO;
import com.neu.hardik.pojo.Task;
import com.neu.hardik.pojo.User;
import com.neu.hardik.utility.Constants.Status;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
	@Autowired
	private TaskDAO taskDao;

	private static final Logger LOGGER = Logger.getLogger(TaskController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addTask(Task task) {
		Task addTask = taskDao.addTask(task);
		LOGGER.info("Adding new task : " + addTask.getTaskId());
	}

	@RequestMapping(value = "/get/{taskId}", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody Task getTask(@PathVariable long taskId) {
		LOGGER.info("Retrieving taskID " + taskId);
		return taskDao.getTask(taskId);
	}

	@RequestMapping(value = "/update/{taskId}", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateTask(HttpServletRequest request, @PathVariable long taskId) {
		taskDao.updateTask(taskId, Status.valueOf(request.getParameter("status")), request.getParameter("comment"));
		LOGGER.info("Status for taskID " + taskId + " updated to " + Status.valueOf(request.getParameter("status")));
	}

	@RequestMapping(value = "/mytask")
	public String myTask(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("tasks", taskDao.getTasksForUser(user));
		return "dashboard";
	}

	@RequestMapping(value = "/search")
	public String getTasks(Model model, HttpServletRequest request) {
		String type = request.getParameter("type");
		String query = request.getParameter("query");
		model.addAttribute("results", taskDao.getSearchTask(query, type));
		return "search-results";
	}
}