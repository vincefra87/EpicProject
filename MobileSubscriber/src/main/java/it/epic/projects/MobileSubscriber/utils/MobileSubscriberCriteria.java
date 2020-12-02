package it.epic.projects.MobileSubscriber.utils;

import java.util.Date;

import it.epic.projects.MobileSubscriber.model.ServiceTypeEnum;

/**
 * @author Vincenzo Francavilla
 * 
 * Class that managed the criteria to interact with the db
 * 
 * */
public class MobileSubscriberCriteria {
	
	private String  msisdn;
	private Integer customerIdOwner;
	private Integer customerIdUser;
	private ServiceTypeEnum serviceType;
	private Date serviceStartDate;
	private Date  fromStartDate;            /* Field to find all numbers that start date it is after this one */
	private Date  untilEndDate;             /* Field to find all numbers that start date it is no later than this one */
	private String sort;                    /* Field to define sort */
	
	
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Integer getCustomerIdOwner() {
		return customerIdOwner;
	}
	public void setCustomerIdOwner(Integer customerIdOwner) {
		this.customerIdOwner = customerIdOwner;
	}
	public Integer getCustomerIdUser() {
		return customerIdUser;
	}
	public void setCustomerIdUser(Integer customerIdUser) {
		this.customerIdUser = customerIdUser;
	}
	public ServiceTypeEnum getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceTypeEnum serviceType) {
		this.serviceType = serviceType;
	}
	public Date getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	public Date getFromStartDate() {
		return fromStartDate;
	}
	public void setFromStartDate(Date fromStartDate) {
		this.fromStartDate = fromStartDate;
	}
	public Date getUntilEndDate() {
		return untilEndDate;
	}
	public void setUntilEndDate(Date untilEndDate) {
		this.untilEndDate = untilEndDate;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}

