package it.epic.projects.MobileSubscriber.queryFactory;

import it.epic.projects.MobileSubscriber.model.MobileSubscriber;
import it.epic.projects.MobileSubscriber.utils.MobileSubscriberCriteria;

/**
 * @author Vincenzo Francavilla
 * 
 * Class that managed the query on the db
 * 
 * */
public class MobileSubscriberManagerQuery {
	
	public String findByCriteria(MobileSubscriberCriteria criteria) {
		StringBuilder statement = new StringBuilder();
		statement.append(" SELECT ");
		statement.append(" ID, MSISDN, CUSTOMER_ID_OWNER, CUSTOMER_ID_USER, SERVICE_TYPE, SERVICE_START_DATE ");
		statement.append(" FROM MOBILE_SUBSCRIBES ");
		statement.append(" WHERE 1=1 ");
		if (criteria != null) {
			if(!criteria.getMsisdn().isEmpty()) {
				statement.append(" AND MSISDN = ").append(criteria.getMsisdn());	
			}
			if(criteria.getCustomerIdOwner() != null) {
				statement.append(" AND CUSTOMER_ID_OWNER = ").append(criteria.getCustomerIdOwner());	
			}
			if(criteria.getCustomerIdUser() != null) {
				statement.append(" AND CUSTOMER_ID_USER = ").append(criteria.getCustomerIdUser());	
			}
			if(criteria.getServiceType() != null) {
				statement.append(" AND SERVICE_TYPE = ").append(criteria.getServiceType().getServiceType());	
			}
			if(criteria.getServiceStartDate() != null) {
				statement.append(" AND SERVICE_START_DATE = ").append(criteria.getServiceStartDate());	
			}
			
			if(criteria.getFromStartDate() != null) {
				statement.append(" AND SERVICE_START_DATE >= ").append(criteria.getServiceStartDate());	
			}
			
			if(criteria.getUntilEndDate() != null) {
				statement.append(" AND SERVICE_START_DATE <= ").append(criteria.getUntilEndDate());	
			}
			
			if(!criteria.getSort().isEmpty()) {
				statement.append(" ORDER BY ").append(criteria.getSort());	
			}
		}
		
		return statement.toString();
	}
	
	public String insert(MobileSubscriber mobileSubscriber) {
		StringBuilder statement = new StringBuilder();
		statement.append(" INSERT INTO ");
		statement.append(" MOBILE_SUBSCRIBES ");
		statement.append(" (ID, MSISDN, CUSTOMER_ID_OWNER, CUSTOMER_ID_USER, SERVICE_TYPE, SERVICE_START_DATE) ");
		statement.append(" VALUES ( ");
		statement.append(" ").append(mobileSubscriber.getId()).append(" , ");
		statement.append(" ").append(mobileSubscriber.getMsisdn()).append(" , ");
		statement.append(" ").append(mobileSubscriber.getCustomerIdOwner()).append(" , ");
		statement.append(" ").append(mobileSubscriber.getCustomerIdUser()).append(" , ");
		statement.append(" ").append(mobileSubscriber.getServiceType().getServiceType()).append(" , ");
		statement.append(" ").append(mobileSubscriber.getServiceStartDate()).append(" ");
		statement.append(" ) ");
		return statement.toString();
	}
	
	/**
	 * Method that create a sequence for ID 
	 * */
	public String sqlSelNewMobileSubscriberID() {
		StringBuilder statement = new StringBuilder();
		
		statement.append(" SELECT ");
		statement.append(" MOBILE_SUBSCRIBER_SEQ.nextval ");
		statement.append(" AS ID ");
		statement.append(" FROM DUAL ");
		
		return statement.toString();
	}
	

	public String update(MobileSubscriber mobileSubscriber) {
		StringBuilder statement = new StringBuilder();
		statement.append(" UPDATE ");
		statement.append(" MOBILE_SUBSCRIBES ");
		statement.append(" SET ID = ID ");
		if(mobileSubscriber.getServiceType() != null) {
			statement.append(" , SERVICE_TYPE = ").append(mobileSubscriber.getServiceType().getServiceType());	
		}
		if(mobileSubscriber.getCustomerIdOwner() != null) {
			statement.append(" , CUSTOMER_ID_OWNER = ").append(mobileSubscriber.getCustomerIdOwner());	
		}
		if(mobileSubscriber.getCustomerIdUser() != null) {
			statement.append(" , CUSTOMER_ID_USER = ").append(mobileSubscriber.getCustomerIdUser());	
		}	
		statement.append(" WHERE MSISDN = ").append(mobileSubscriber.getMsisdn());
				
		return statement.toString();
	}
	
	public String delete(MobileSubscriber mobileSubscriber) {
		StringBuilder statement = new StringBuilder();
		statement.append(" DELETE ");
		statement.append(" MOBILE_SUBSCRIBES ");
		statement.append(" WHERE MSISDN = ").append(mobileSubscriber.getMsisdn());
		
		if(mobileSubscriber.getCustomerIdOwner() != null) {
			statement.append(" AND CUSTOMER_ID_OWNER = ").append(mobileSubscriber.getCustomerIdOwner());	
		}
		if(mobileSubscriber.getCustomerIdUser() != null) {
			statement.append(" AND CUSTOMER_ID_USER = ").append(mobileSubscriber.getCustomerIdUser());	
		}
		if(mobileSubscriber.getServiceType() != null) {
			statement.append(" AND SERVICE_TYPE = ").append(mobileSubscriber.getServiceType().getServiceType());	
		}
		if(mobileSubscriber.getServiceStartDate() != null) {
			statement.append(" AND SERVICE_START_DATE = ").append(mobileSubscriber.getServiceStartDate());	
		}
			
		return statement.toString();
	}


}
