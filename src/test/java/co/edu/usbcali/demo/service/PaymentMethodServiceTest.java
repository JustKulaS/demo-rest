package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.PaymentMethod;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class PaymentMethodServiceTest {

private final static  Logger log=LoggerFactory.getLogger(PaymentMethod.class);
	
	private static Integer payId=null;
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	
	@Test
	@Order(1)
	void save() throws Exception {
		log.info("save");
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("Efecty");		
		paymentMethod=paymentMethodService.save(paymentMethod);
		payId=paymentMethod.getPayId();
		assertNotNull(payId, "El payId es nulo");
		
		log.info("PayId: " + payId);
	}
	
	@Test
	@Order(2)
	void findById() throws Exception {
		log.info("findbyId");
		assertTrue(paymentMethodService.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodService.findById(payId).get();
		
		assertNotNull(paymentMethod,"el payment method no existe");
	}
	
	@Test
	@Order(3)
	void update() throws Exception {
		log.info("update");
		assertTrue(paymentMethodService.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodService.findById(payId).get();
		paymentMethod.setEnable("N");
		
		paymentMethodService.update(paymentMethod);
	}
	
	@Test
	@Order(4)
	void delete() throws Exception {
		log.info("delete");
		assertTrue(paymentMethodService.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodService.findById(payId).get();
		paymentMethodService.delete(paymentMethod);
		
	}
	@Test
	@Order(5)
	void count() throws Exception {
		log.info("conteo: " + paymentMethodService.count());
	}
}
