package it.epic.projects.MobileSubscriber.model;

/**
 * @author Vincenzo Francavilla
 * 
 * Class, to describe the service type
 * 
 * */
public enum ServiceTypeEnum {
	
	MOBILE_PREPAID("MOBILE_PREPAID"),
	MOBILE_POSTPAID("MOBILE_POSTPAID");

    private String serviceType = null;

    ServiceTypeEnum(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return this.serviceType;
    }
	
}