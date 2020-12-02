package it.epic.projects.MobileSubscriber.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Vincenzo Francavilla
 * 
 * Class, to describe the mobile subscriber mapped on database (Entity)
 * 
 * */
public class MobileSubscriber {
		
	private Integer Id;
	private String  msisdn;
	private Integer customerIdOwner;       /* Should be a Foreign Key to another table where there are all Owner */
	private Integer customerIdUser;        /* Should be a Foreign Key to another table where there are all User  */
	private ServiceTypeEnum serviceType;
	private Date serviceStartDate;
	
	public MobileSubscriber() {}
	
	public MobileSubscriber(Integer Id, String  msisdn, Integer customerIdOwner, Integer customerIdUser, ServiceTypeEnum serviceType, Date serviceStartDate) {
		super();
		this.Id = Id;
		this.msisdn = msisdn;
		this.customerIdOwner = customerIdOwner;
		this.customerIdUser = customerIdUser;
		this.serviceType = serviceType;
		this.serviceStartDate = serviceStartDate;
	}
	
	public MobileSubscriber(ResultSet rst) throws SQLException {
		this(rst,"");
	}
	
	public MobileSubscriber(ResultSet rst, String prefix) throws SQLException {
		super();
		
		this.setId(rst.getInt("ID"));
		this.setMsisdn(rst.getString("MSISDN"));
		this.setCustomerIdOwner(rst.getInt("CUSTOMER_ID_OWNER"));
		this.setCustomerIdUser(rst.getInt("CUSTOMER_ID_USER"));
		
		if (rst.getString("SERVICE_TYPE") != null && ServiceTypeEnum.valueOf(rst.getString("SERVICE_TYPE")) != null){
			this.setServiceType(ServiceTypeEnum.valueOf(rst.getString("SERVICE_TYPE")));	
		}
		this.setServiceStartDate(rst.getDate("SERVICE_START_DATE"));
		
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
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

}
