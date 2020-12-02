package it.epic.projects.MobileSubscriber.restServices;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.epic.projects.MobileSubscriber.model.MobileSubscriber;
import it.epic.projects.MobileSubscriber.services.IMobileSubscriberService;
import it.epic.projects.MobileSubscriber.utils.MobileSubscriberCriteria;

/**
 * @author Vincenzo Francavilla
 * 
 * Rest Controller
 * 
 * */
@Api(value = "MobileSubscriberRS", description = "REST APIs related to Mobile Subscribers")
@RestController
public class MobileSubscriberRS {
	
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IMobileSubscriberService mobileSubscriberServiceCustom;
	
    /*
	 * Rest method return all mobile numbers from the database
	 * 
	 * */
	@ApiOperation(value = "Rest method return all mobile numbers from the database", response = Iterable.class, tags = "all-numbers")
	@GetMapping(path = "/all-numbers")
	public ResponseEntity<List<MobileSubscriber>> getAll() throws Exception {
		logger.info(" Start MobileSubscriberRS.getAll method ");
	    CompletableFuture<List<MobileSubscriber>> result = null;
	    
		try {
			result = mobileSubscriberServiceCustom.getAllNumbers();
		} catch (Exception e) {
			logger.info(" Error in MobileSubscriberRS.getAll method " + e);
			throw new Exception("  Error in MobileSubscriberRS.getAll method " + e.getMessage());
		}
		
		logger.info(" End MobileSubscriberRS.getAll method ");
		return new ResponseEntity<>(result.join(), HttpStatus.OK);
	}
    
	/*
	 * Rest method return all mobile numbers that match	the search criteria 
	 * 
	 * */
	@ApiOperation(value = "Rest method return all mobile numbers that match	the search criteria", response = Iterable.class, tags = "find")
	@GetMapping(path = "/find")
	public ResponseEntity<List<MobileSubscriber>> find(MobileSubscriberCriteria parameter) throws Exception {
		logger.info(" Start MobileSubscriberRS.find method ");
		CompletableFuture<List<MobileSubscriber>> result = null;
		try {
			result = mobileSubscriberServiceCustom.findMobileSubscriber(parameter);
		} catch (Exception e) {
			logger.info(" Error in MobileSubscriberRS.find method " + e);
			throw new Exception("  Error in MobileSubscriberRS.find method " + e.getMessage());
		}
		
		logger.info(" End MobileSubscriberRS.find method ");
		return new ResponseEntity<>(result.join(), HttpStatus.OK);
	}
	
	/*
	 * Rest method add a mobile number to the database. I assumed a unique INDEX on db on msisdn column 
	 * 
	 * */
	@ApiOperation(value = "Rest method add a mobile number to the database. I assumed a unique INDEX on db on msisdn column", response = MobileSubscriber.class, tags = "create")
	@PostMapping(path = "/create")
	public ResponseEntity<MobileSubscriber> create(@RequestBody MobileSubscriber parameter) throws Exception {
		logger.info(" Start MobileSubscriberRS.create method ");
		CompletableFuture<MobileSubscriber> result = null;
		try {
			result = mobileSubscriberServiceCustom.createMobileSubscriber(parameter);
		} catch (Exception e) {
			logger.info(" Error in MobileSubscriberRS.create method " + e);
			throw new Exception("  Error in MobileSubscriberRS.create method " + e.getMessage());
		}
		
		logger.info(" End MobileSubscriberRS.create method ");
		return new ResponseEntity<>(result.join(), HttpStatus.OK);
	}
		
	/*
	 * Rest method change a mobile number plan from prepaid to postpaid or vice versa or assign different owners / users of a service.
	 * 
	 * */
	@ApiOperation(value = "Rest method change a mobile number plan from prepaid to postpaid or vice versa or assign different owners / users of a service.", response = MobileSubscriber.class, tags = "update-data")
	@PatchMapping(path = "/update-data")
	public ResponseEntity<MobileSubscriber> update(@RequestBody MobileSubscriber parameter) throws Exception {
		logger.info(" Start MobileSubscriberRS.update method ");
		CompletableFuture<MobileSubscriber> result = null;
		try {
			result = mobileSubscriberServiceCustom.updateMobileSubscriber(parameter);
		} catch (Exception e) {
			logger.info(" Error in MobileSubscriberRS.update method " + e);
			throw new Exception("  Error in MobileSubscriberRS.update method " + e.getMessage());
		}
		
		logger.info(" End MobileSubscriberRS.update method ");
		return new ResponseEntity<>(result.join(), HttpStatus.OK);
	}
	
	/*
	 * Rest method delete a mobile number from the database
	 * 
	 * */
	@ApiOperation(value = "Rest method delete a mobile number from the database", response = MobileSubscriber.class, tags = "delete")
	@DeleteMapping(path = "/delete")
	public ResponseEntity<Boolean> delete(@RequestBody String msisdn) throws Exception {
		logger.info(" Start MobileSubscriberRS.delete method ");
		CompletableFuture<Boolean> result = null;
		MobileSubscriber ms = new MobileSubscriber(); ms.setMsisdn(msisdn);
		try {
			result = mobileSubscriberServiceCustom.deleteMobileSubscriber(ms);
		} catch (Exception e) {
			logger.info(" Error in MobileSubscriberRS.delete method " + e);
			throw new Exception("  Error in MobileSubscriberRS.delete method " + e.getMessage());
		}
		
		logger.info(" End MobileSubscriberRS.delete method ");
		return new ResponseEntity<>(result.join(), HttpStatus.OK);
		
	}
	
}

