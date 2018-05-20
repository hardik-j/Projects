package com.neu.hardik.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.neu.hardik.pojo.User;

public class UserDAO extends DAO {
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

	public User addUser(User user) {
		User checkUser = getUser(user, true);
		if (checkUser == null) {
			try {
				begin();
				getSession().save(user);
				commit();

			} catch (Exception e) {
				// TODO: handle exception
				LOGGER.error("Couldn't add the user!!");
				rollback();
			} finally {
				close();
			}
			return user;
		} else
			return null;
	}

	public List<User> getAllUsers() {
		List<User> list = null;
		try {
			begin();
			Criteria criteria = getSession().createCriteria(User.class);
			list = (List<User>) criteria.list();
			commit();
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Couldn't find the users!!");
			rollback();
		} finally {
			close();
		}
		return list;
	}

	public User getUser(User user, boolean check) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", user.getUsername()));
		if (!check)
			criteria.add(Restrictions.eq("password", user.getPassword()));
		User u = (User) criteria.uniqueResult();
		return u;
	}

	public List<User> getTeamMembers(String teamName) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("team", teamName));
		List<User> list = criteria.list();
		return list;
	}

}
