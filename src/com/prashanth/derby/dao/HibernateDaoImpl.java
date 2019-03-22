package com.prashanth.derby.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//Incomplete
public class HibernateDaoImpl {
	@Autowired
	private SessionFactory sessionFactory; 
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int getCircleCount() {
		String hql = "select count(*) from cicle";
		@SuppressWarnings("deprecation")
		Query<Integer> query = getSessionFactory().openSession().createQuery(hql);
		return ((Integer)query.uniqueResult()).intValue();
	}
}