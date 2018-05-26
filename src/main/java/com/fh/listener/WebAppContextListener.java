package com.fh.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fh.util.Logger;

import net.sf.ehcache.CacheManager;
/**
 * 
* 类名称：WebAppContextListener.java
* 类描述： 
* 作者：FH 
* 联系方式：
* @version 1.0
 */
public class WebAppContextListener implements ServletContextListener {

	protected Logger log = Logger.getLogger(this.getClass());

	public void contextInitialized(ServletContextEvent event) {
	}

	public final void contextDestroyed(ServletContextEvent sce) {

		log.info("Destroying Context...");

		try {
			log.info("Calling MySQL AbandonedConnectionCleanupThread shutdown");
			com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();

		} catch (InterruptedException e) {
			log.error("Error calling MySQL AbandonedConnectionCleanupThread shutdown {}", e);
		}

		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();

			if (driver.getClass().getClassLoader() == cl) {

				try {
					log.info("Deregistering JDBC driver {}" + driver);
					DriverManager.deregisterDriver(driver);

				} catch (SQLException ex) {
					log.error("Error deregistering JDBC driver {}" + driver, ex);
				}

			} else {
				log.info("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader" + driver);
			}
		}
		
		CacheManager.getInstance().shutdown();
	}

}
