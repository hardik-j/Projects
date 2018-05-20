package com.neu.hardik.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.neu.hardik.controller.TaskController;
import com.neu.hardik.pojo.Task;
import com.neu.hardik.pojo.User;
import com.neu.hardik.utility.Constants.Status;

public class TaskDAO extends DAO {
	
	private static final Logger LOGGER = Logger.getLogger(TaskController.class);
	public Task addTask(Task task) {
		try {
			begin();
			getSession().save(task);
			commit();
		} catch (Exception e) {
			rollback();
		} finally {
			close();
		}
		return task;
	}

	public List<Task> getTasksForUser(User user) {
		Criteria criteria = getSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("user", user));
		List<Task> list = criteria.list();
		return list;
	}

	public List<Task> getTasksForMeeting(long meetingId) {
		Criteria criteria = getSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("meeting_meetingId", meetingId));
		List<Task> list = criteria.list();
		return list;
	}

	public Task updateTask(long taskId, Status status, String comment) {
		Task task = null;
		try {
			begin();
			Criteria criteria = getSession().createCriteria(Task.class);
			criteria.add(Restrictions.eq("taskId", new Long(taskId)));
			task = (Task) criteria.list().get(0);
			task.setStatus(status);
			task.setComment(comment);
			getSession().update(task);
			commit();
		} catch (Exception e) {
			rollback();
		} finally {
			close();
		}
		return task;
	}

	public Task getTask(long taskId) {
		Criteria criteria = getSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("taskId", new Long(taskId)));
		return (Task) criteria.list().get(0);
	}

	public List<Task> getSearchTask(String query, String type) {
		Criteria criteria = getSession().createCriteria(Task.class);
		if (type.equalsIgnoreCase("user")) {
			criteria.createAlias("user", "u");
			LogicalExpression expression = Restrictions.or(
					Restrictions.like("u.firstName", query, MatchMode.ANYWHERE),
					Restrictions.like("u.lastName", query, MatchMode.ANYWHERE));
			LogicalExpression expression2 = Restrictions.or(expression,
					Restrictions.like("u.username", query, MatchMode.ANYWHERE));
			criteria.add(expression2);
		} else if (type.equalsIgnoreCase("status")) {
			criteria.add(Restrictions.eq("status", Status.valueOf(query.toUpperCase())));
		} else if (type.equalsIgnoreCase("id")) {
			criteria.add(Restrictions.eq("taskId", Long.parseLong(query)));
		}
		return criteria.list();
	}
}