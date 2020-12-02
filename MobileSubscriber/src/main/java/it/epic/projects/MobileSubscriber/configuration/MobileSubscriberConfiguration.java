package it.epic.projects.MobileSubscriber.configuration;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import it.epic.projects.MobileSubscriber.services.IMobileSubscriberService;
import it.epic.projects.MobileSubscriber.services.MobileSubscriberService;
import it.epic.projects.MobileSubscriber.services.MobileSubscriberServiceMock;

/**
 * @author Vincenzo Francavilla
 * 
 * Configurator IOC Spring for Mobile Subscriber Application
 * 
 * */
@Configuration
@ComponentScan
public class MobileSubscriberConfiguration {

	@Bean
	@Autowired
	public IMobileSubscriberService mobileSubscriberService(){
		boolean isMock = true;
		if(isMock) {
			return new MobileSubscriberServiceMock();
		} else {
			return new MobileSubscriberService();
		}
	}
	
	/**
	 * The connection to the db should be set here
	 * */
	@Bean
	public DataSource dataSource() {
		return new DataSource() {
			
			@Override
			public <T> T unwrap(Class<T> iface) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isWrapperFor(Class<?> iface) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void setLoginTimeout(int seconds) throws SQLException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setLogWriter(PrintWriter out) throws SQLException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Logger getParentLogger() throws SQLFeatureNotSupportedException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getLoginTimeout() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public PrintWriter getLogWriter() throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Connection getConnection(String username, String password) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Connection getConnection() throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
