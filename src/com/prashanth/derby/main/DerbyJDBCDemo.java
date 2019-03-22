package com.prashanth.derby.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.prashanth.derby.dao.DerbyJdbcDaoImpl;
import com.prashanth.derby.dao.HibernateDaoImpl;
import com.prashanth.derby.dao.NamedParameterDaoImpl;
import com.prashanth.derby.model.Circle;

public class DerbyJDBCDemo {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		DerbyJdbcDaoImpl dao = ctx.getBean("derbyJdbcDaoImpl", DerbyJdbcDaoImpl.class);
		//Circle circle = dao.getCircle(1);
		//System.out.println(circle.getName());
		System.out.println(dao.getCircleCount());
		
		System.out.println(dao.getCircleName(1));
		
		System.out.println(dao.getCircleForID(1).getId() + "-"
							+ dao.getCircleForID(1).getName()
							+"- Using queryForObject() to return circleMapper");
		
		//dao.insertCircle(new Circle (5, "Fifth Circle"));
		
		System.out.println(dao.getAllCircles().size()+"- Using query() to return List");
		
		//dao.createTable();
		
		/*Incomplete
		//NamedParameterDaoImpl dao = ctx.getBean("namedParameterDaoImpl", NamedParameterDaoImpl.class);
		//System.out.println(dao.getCircleCount());
		*/
		
		/*Incomplete
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		HibernateDaoImpl dao = ctx.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
		System.out.println(dao.getCircleCount());
		*/
		}
}