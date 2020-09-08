package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.PaymentMethod;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class PaymentMethodRepositoryTest {
	private final static  Logger log=LoggerFactory.getLogger(PaymentMethod.class);
	
	private static Integer payId=null;
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	
	@Test
	@Order(1)
	void save() {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("Efecty");
		
		paymentMethod=paymentMethodRepository.save(paymentMethod);
		payId=paymentMethod.getPayId();
		assertNotNull(payId, "El payId es nulo");
		
		log.info("PayId: " + payId);
	}
	
	@Test
	@Order(2)
	void findById() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		
		assertNotNull(paymentMethod,"el payment method no existe");
	}
	
	@Test
	@Order(3)
	void update() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		paymentMethod.setEnable("N");
		
		paymentMethodRepository.save(paymentMethod);
	}
	
	@Test
	@Order(4)
	void delete() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		paymentMethodRepository.delete(paymentMethod);
		
	}

}
