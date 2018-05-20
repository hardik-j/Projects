package com.neu.hardik.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.neu.hardik.pojo.Meeting;

public class MeetingDAO extends DAO {
	private static final Logger LOGGER = Logger.getLogger(MeetingDAO.class);

	public Meeting addMeeting(Meeting meeting) {
		try {
			begin();
			getSession().save(meeting);
			commit();
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Couldn't add the user!!");
			rollback();
		} finally {
			close();
		}
		return meeting;
	}
	
	public List<Meeting> getMeetings(String team) {
		Criteria criteria = getSession().createCriteria(Meeting.class);
		criteria.add(Restrictions.eq("team", team));
		List<Meeting> list = criteria.list();
		return list;
	}

}
