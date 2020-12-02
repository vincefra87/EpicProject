package it.epic.projects.MobileSubscriber.services;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import it.epic.projects.MobileSubscriber.model.MobileSubscriber;
import it.epic.projects.MobileSubscriber.utils.MobileSubscriberCriteria;


/**
 * @author Vincenzo Francavilla
 * 
 * Interface that exposes logical methods
 * 
 * */
@Service
public interface IMobileSubscriberService {
	
	CompletableFuture<List<MobileSubscriber>> findMobileSubscriber(MobileSubscriberCriteria criteria) throws SQLException;
	
	CompletableFuture<List<MobileSubscriber>> getAllNumbers();
	
	CompletableFuture<MobileSubscriber> createMobileSubscriber(MobileSubscriber mobileSubscriber) throws Exception;
	
	CompletableFuture<MobileSubscriber> updateMobileSubscriber(MobileSubscriber mobileSubscriber) throws Exception;

	CompletableFuture<Boolean> deleteMobileSubscriber(MobileSubscriber mobileSubscriber) throws Exception;
}
