package com.pizzeria.contextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateApplicationContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Server is starting =============================================================>");
	}

	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("Server has been Killed !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
	}
}
