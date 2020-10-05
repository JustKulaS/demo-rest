package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;



@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTest {

	private final static  Logger log=LoggerFactory.getLogger(CustomerServiceTest.class);
	
	private final static String email="gian_c1800@hotmail.com";
	
	@Autowired
	CustomerService customerService;
	
	
	@Test
	@Order(1)
	void save()throws Exception {
		log.info("save");
		
		Customer customer = new Customer();
		customer.setAddress("lo que sea 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("giancarlo");
		customer.setPhone("1213 131 41");
		customer.setToken("NKASJDKAD34353KJ");
		
		customerService.save(customer);
	}
	
	@Test
	@Order(2)
	void findByID()throws Exception {
		log.info("findById");
		
		Optional<Customer> customerOptional=customerService.findById(email);
		
		assertTrue(customerOptional.isPresent(), "El customer no existe");
				
	}

	@Test
	@Order(3)
	void update()throws Exception {
		log.info("save");
		Optional<Customer> customerOptional=customerService.findById(email);
		
		assertTrue(customerOptional.isPresent(), "El customer no existe");
		
		Customer customer=customerOptional.get();
		
		customer.setEnable("Y");
		
		customerService.update(customer);
	}
		
	
	@Test
	@Order(4)
	void delete()throws Exception {
		log.info("save");
		Optional<Customer> customerOptional=customerService.findById(email);
		
		assertTrue(customerOptional.isPresent(), "El customer no existe");
		
		Customer customer=customerOptional.get();
		
		customerService.delete(customer);
	}

}
