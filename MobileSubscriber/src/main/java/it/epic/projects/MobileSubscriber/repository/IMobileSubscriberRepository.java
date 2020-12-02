package it.epic.projects.MobileSubscriber.repository;

import java.sql.SQLException;
import java.util.List;

import it.epic.projects.MobileSubscriber.model.MobileSubscriber;
import it.epic.projects.MobileSubscriber.utils.MobileSubscriberCriteria;


/**
 * @author Vincenzo Francavilla
 * 
 * Interface to export all method to use in Service class to access the db
 * 
 * */
public interface IMobileSubscriberRepository {
	
	List<MobileSubscriber> findByCriteria(MobileSubscriberCriteria criteria) throws SQLException;
	
	MobileSubscriber create(MobileSubscriber mobileSubscriber) throws SQLException;
	
	Integer getNewMobileSubscribeId() throws SQLException;
	
	MobileSubscriber update(MobileSubscriber mobileSubscriber) throws SQLException;
	
	boolean delete(MobileSubscriber mobileSubscriber) throws SQLException;
}
