package com.stackroute.activitystream.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.stackroute.activitystream.config.HibernateUtil;
import com.stackroute.activitystream.model.Message;

public class MessageRepository {

	Session session;
	
	public MessageRepository() {
		session=HibernateUtil.getSessionFactory().openSession();
	}
	
	public boolean sendMessage(Message message) {
		session.beginTransaction();

		session.save(message);
		session.getTransaction().commit();
		return true;

		
	}
	
	public List<Message> getAllMessages(){
		session.beginTransaction();
		Query query = session.createQuery("from Message order by postedDate desc");
		List<Message> messages = query.list();
		session.getTransaction().commit();
		return messages;
	}
}
