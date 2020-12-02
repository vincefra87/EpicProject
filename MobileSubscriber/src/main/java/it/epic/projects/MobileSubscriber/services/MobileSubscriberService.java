package it.epic.projects.MobileSubscriber.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import it.epic.projects.MobileSubscriber.model.MobileSubscriber;
import it.epic.projects.MobileSubscriber.repository.IMobileSubscriberRepository;
import it.epic.projects.MobileSubscriber.utils.MobileSubscriberCriteria;

/**
 * @author Vincenzo Francavilla
 * 
 * Class that implements all logic method
 * 
 * */
public class MobileSubscriberService implements IMobileSubscriberService {

	@Autowired
	private IMobileSubscriberRepository mobileSubscriberRepository;
	
	@Override
	public CompletableFuture<List<MobileSubscriber>> findMobileSubscriber(MobileSubscriberCriteria criteria) throws SQLException {
		if (criteria == null) {
			return CompletableFuture.completedFuture(new ArrayList<>());
		}
		
		return CompletableFuture.completedFuture(mobileSubscriberRepository.findByCriteria(criteria));
	}
	
	@Override
	public CompletableFuture<List<MobileSubscriber>> getAllNumbers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<MobileSubscriber> createMobileSubscriber(MobileSubscriber mobileSubscriber) throws Exception {
		//check param validity
		if(!mobileSubscriberParamsAreValid(mobileSubscriber)) {
			throw new Exception("Error - Input parameters not filled correctly!");
		}
		//check that mobile number doesn't exist
		MobileSubscriberCriteria msc = new MobileSubscriberCriteria();
		msc.setMsisdn(mobileSubscriber.getMsisdn());
		List<MobileSubscriber> tempList = mobileSubscriberRepository.findByCriteria(msc);
		if (tempList != null && tempList.size()>0) {
			throw new Exception("Error - Mobile Subscriber already exist!");
		}
		
		//Create the sequence ID and execute insert
		mobileSubscriber.setId(mobileSubscriberRepository.getNewMobileSubscribeId());
		
		mobileSubscriber = mobileSubscriberRepository.create(mobileSubscriber);
		
		return CompletableFuture.completedFuture(mobileSubscriber);
	}

	@Override
	public CompletableFuture<MobileSubscriber> updateMobileSubscriber(MobileSubscriber mobileSubscriber) throws Exception {
		//check param validity. I want to be sure that there is the number(Msisdn) at least
		if (!mobileSubscriberParamsAreValidToUpdate(mobileSubscriber)) {
			throw new Exception("Error - Input parameters not filled correctly to update the mobile subscriber!");
		}
		//check that mobile subscriber exist
		MobileSubscriberCriteria msc = new MobileSubscriberCriteria();
		msc.setMsisdn(mobileSubscriber.getMsisdn());
		List<MobileSubscriber> tempList = mobileSubscriberRepository.findByCriteria(msc);
		if (tempList == null || tempList.isEmpty()) {
			throw new Exception("Error - Mobile Subscriber doesn't exist! Impossible to update the row!");
		}
		
		return CompletableFuture.completedFuture(mobileSubscriberRepository.update(mobileSubscriber));
	}

	@Override
	public CompletableFuture<Boolean> deleteMobileSubscriber(MobileSubscriber mobileSubscriber) throws Exception {
		//check input param. The Msisdn there must be
		if (mobileSubscriber == null || mobileSubscriber.getMsisdn().isEmpty()) {
			throw new Exception("Error - Can't delete number. The field number(Msisdn) must be valued!");
		}
		//check that mobile subscriber exist
		MobileSubscriberCriteria msc = new MobileSubscriberCriteria();
		msc.setMsisdn(mobileSubscriber.getMsisdn());
		List<MobileSubscriber> tempList = mobileSubscriberRepository.findByCriteria(msc);
		if (tempList == null || tempList.isEmpty()) {
			throw new Exception("Error - Mobile Subscriber doesn't exist! Impossible to delete the row!");
		}
		
		return CompletableFuture.completedFuture(mobileSubscriberRepository.delete(mobileSubscriber));
	}
	
	private boolean mobileSubscriberParamsAreValid(MobileSubscriber mobileSubscriber) {
		return !(mobileSubscriber == null || mobileSubscriber.getMsisdn().isEmpty() || mobileSubscriber.getCustomerIdOwner() == null 
				|| mobileSubscriber.getCustomerIdUser() == null || mobileSubscriber.getServiceType() == null || mobileSubscriber.getServiceStartDate() == null);
	}

	private boolean mobileSubscriberParamsAreValidToUpdate(MobileSubscriber mobileSubscriber) {
		return !(mobileSubscriber == null || mobileSubscriber.getMsisdn().isEmpty() || (mobileSubscriber.getCustomerIdOwner() == null 
				&& mobileSubscriber.getCustomerIdUser() == null && mobileSubscriber.getServiceType() == null));
	}

	
}
