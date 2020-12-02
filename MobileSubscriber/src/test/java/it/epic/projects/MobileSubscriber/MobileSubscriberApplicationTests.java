package it.epic.projects.MobileSubscriber;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import it.epic.projects.MobileSubscriber.model.MobileSubscriber;
import it.epic.projects.MobileSubscriber.model.ServiceTypeEnum;
import it.epic.projects.MobileSubscriber.services.MobileSubscriberServiceMock;
import it.epic.projects.MobileSubscriber.utils.MobileSubscriberCriteria;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MobileSubscriberApplicationTests {

	private MobileSubscriberServiceMock mobileSubscriberServiceCustom = new MobileSubscriberServiceMock();
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getAllMobileSubscribersTest() {
		assertNotNull(mobileSubscriberServiceCustom.getAllNumbers());
	}
	
	@Test
	void findMobileSubscribersTest() throws SQLException {
		MobileSubscriberCriteria msc = new MobileSubscriberCriteria();
		msc.setMsisdn("3490010000");
		assertNotNull(mobileSubscriberServiceCustom.findMobileSubscriber(msc));
		
		msc.setMsisdn("3");
		assertTrue(mobileSubscriberServiceCustom.findMobileSubscriber(msc).join().isEmpty());
	}

	@Test
	void createMobileSubscribersTest() throws Exception {
		MobileSubscriber ms = new MobileSubscriber();
		ms.setMsisdn("3410000000");
		ms.setCustomerIdOwner(311);
		ms.setCustomerIdUser(311);
		ms.setServiceStartDate(new Date());
		ms.setServiceType(ServiceTypeEnum.MOBILE_PREPAID);
		assertNotNull(mobileSubscriberServiceCustom.createMobileSubscriber(ms));
	}
	
	@Test
	void updateMobileSubscribersTest() throws Exception {
		MobileSubscriber ms = new MobileSubscriber();
		ms.setMsisdn("3410000000");
		ms.setCustomerIdOwner(311);
		ms.setCustomerIdUser(311);
		ms.setServiceStartDate(new Date());
		ms.setServiceType(ServiceTypeEnum.MOBILE_POSTPAID);
		assertNotNull(mobileSubscriberServiceCustom.updateMobileSubscriber(ms));
	}

	@Test
	void deleteMobileSubscribersTest() throws Exception {
		MobileSubscriber ms = new MobileSubscriber();
		ms.setMsisdn("3410000000");
		
		assertNotNull(mobileSubscriberServiceCustom.deleteMobileSubscriber(ms));
	}
	
}
