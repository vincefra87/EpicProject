package it.epic.projects.MobileSubscriber.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;

import it.epic.projects.MobileSubscriber.model.MobileSubscriber;
import it.epic.projects.MobileSubscriber.model.ServiceTypeEnum;
import it.epic.projects.MobileSubscriber.utils.MobileSubscriberCriteria;

/**
 * @author Vincenzo Francavilla
 * 
 * Class that implements all logic method with a mock mobile subcribers list. Mock class
 * 
 * */
public class MobileSubscriberServiceMock implements IMobileSubscriberService {

	LocalDate date2 = LocalDate.now().minusDays(2);
	LocalDate date4 = LocalDate.now().minusDays(4); 
	
	List<MobileSubscriber> mobileSubscribers = new ArrayList<MobileSubscriber>();
    {
    	mobileSubscribers.add(new MobileSubscriber(1,"3490010000",123,11,ServiceTypeEnum.MOBILE_POSTPAID,new Date()));
    	mobileSubscribers.add(new MobileSubscriber(2,"3490010011",123,11,ServiceTypeEnum.MOBILE_POSTPAID,Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    	mobileSubscribers.add(new MobileSubscriber(3,"3460010600",124,18,ServiceTypeEnum.MOBILE_POSTPAID,Date.from(date4.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    	mobileSubscribers.add(new MobileSubscriber(4,"3490014003",133,15,ServiceTypeEnum.MOBILE_PREPAID,Date.from(date4.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    	mobileSubscribers.add(new MobileSubscriber(5,"3420010018",111,14,ServiceTypeEnum.MOBILE_POSTPAID,Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    	mobileSubscribers.add(new MobileSubscriber(6,"3420010014",111,14,ServiceTypeEnum.MOBILE_PREPAID,Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }
	@Override
	public CompletableFuture<List<MobileSubscriber>> findMobileSubscriber(MobileSubscriberCriteria criteria) throws SQLException {
		if (criteria == null) {
			return CompletableFuture.completedFuture(new ArrayList<>());
		}
		List<MobileSubscriber> result = new ArrayList<>();
		try {		
			Predicate<MobileSubscriber> msisdnPredicate = m-> (criteria.getMsisdn() == null || criteria.getMsisdn().isEmpty()) || (!criteria.getMsisdn().isEmpty() && criteria.getMsisdn().equals(m.getMsisdn()));
			Predicate<MobileSubscriber> customerIdOwnerPredicate = m-> criteria.getCustomerIdOwner() == null || (criteria.getCustomerIdOwner() != null && criteria.getCustomerIdOwner().equals(m.getCustomerIdOwner())) ;
			Predicate<MobileSubscriber> customerIdUserPredicate = m-> criteria.getCustomerIdUser() == null || (criteria.getCustomerIdUser() != null && criteria.getCustomerIdUser().equals(m.getCustomerIdUser())) ;
			Predicate<MobileSubscriber> serviceTypePredicate = m-> criteria.getServiceType() == null || (criteria.getServiceType().getServiceType().equals(m.getServiceType().getServiceType())) ;
			Predicate<MobileSubscriber> fromStartDatePredicate = m-> criteria.getFromStartDate() == null || (criteria.getFromStartDate().before(m.getServiceStartDate()) || (criteria.getFromStartDate().equals(m.getServiceStartDate())));
			Predicate<MobileSubscriber> untilEndDatePredicate = m-> criteria.getUntilEndDate() == null || (criteria.getUntilEndDate().after(m.getServiceStartDate())) || (criteria.getUntilEndDate().equals(m.getServiceStartDate()));
			
			result = mobileSubscribers.stream()
		    .filter(msisdnPredicate.and(customerIdOwnerPredicate).and(customerIdUserPredicate).and(serviceTypePredicate).and(fromStartDatePredicate).and(untilEndDatePredicate))
		    .collect(Collectors.toList());
			
		}catch(Exception e) {
			return CompletableFuture.completedFuture(result);
		}
		
		return CompletableFuture.completedFuture(result);
		
	}
		
	@Async
	@Override
	public CompletableFuture<List<MobileSubscriber>> getAllNumbers() {
		return CompletableFuture.completedFuture(mobileSubscribers);
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
		CompletableFuture<List<MobileSubscriber>> tempList = findMobileSubscriber(msc);
		if (tempList != null && tempList.join().size()>0) {
			throw new Exception("Error - Mobile Subscriber already exist!");
		}
		
		//Create the sequence ID and execute insert
		mobileSubscriber.setId(mobileSubscribers.size()+1);
		
		mobileSubscribers.add(mobileSubscriber);
		
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
		CompletableFuture<List<MobileSubscriber>> tempList = findMobileSubscriber(msc);
		if (tempList == null || tempList.join().isEmpty()) {
			throw new Exception("Error - Mobile Subscriber doesn't exist! Impossible to update the row!");
		}
		
		mobileSubscriber.setId(tempList.join().get(0).getId());
		//I remove old reference
		deleteMobileSubscriber(tempList.join().get(0));
		
		mobileSubscribers.add(mobileSubscriber);
		
		return CompletableFuture.completedFuture(mobileSubscriber);
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
		CompletableFuture<List<MobileSubscriber>> tempList = findMobileSubscriber(msc);
		if (tempList == null || tempList.join().isEmpty()) {
			throw new Exception("Error - Mobile Subscriber doesn't exist! Impossible to delete the row!");
		}
		
		ListIterator<MobileSubscriber> iter = mobileSubscribers.listIterator();
		while(iter.hasNext()){
		    if(iter.next().getMsisdn().equals(mobileSubscriber.getMsisdn())){
		        iter.remove();
		    }
		}
		
		return CompletableFuture.completedFuture(Boolean.TRUE);
	}
	
	
	private boolean mobileSubscriberParamsAreValid(MobileSubscriber mobileSubscriber) {
		return !(mobileSubscriber == null || mobileSubscriber.getMsisdn() == null || mobileSubscriber.getMsisdn().isEmpty() || mobileSubscriber.getCustomerIdOwner() == null 
				|| mobileSubscriber.getCustomerIdUser() == null || mobileSubscriber.getServiceType() == null || mobileSubscriber.getServiceStartDate() == null);
	}

	private boolean mobileSubscriberParamsAreValidToUpdate(MobileSubscriber mobileSubscriber) {
		return !(mobileSubscriber == null || mobileSubscriber.getMsisdn() == null || mobileSubscriber.getMsisdn().isEmpty() || (mobileSubscriber.getCustomerIdOwner() == null 
				&& mobileSubscriber.getCustomerIdUser() == null && mobileSubscriber.getServiceType() == null));
	}
	
}
