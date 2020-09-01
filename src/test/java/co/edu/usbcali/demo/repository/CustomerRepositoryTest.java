package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {

	private final static  Logger log=LoggerFactory.getLogger(CustomerRepositoryTest.class);
	
	private final static String email="gian_c13@hotmail.com";
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("save");
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		assertFalse(customerOptional.isPresent(), "El customer ya existe");
		
		Customer customer = new Customer();
		customer.setAddress("lo que sea 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("gian");
		customer.setPhone("1213 131 41");
		customer.setToken("NKASJDKAD34353KJ");

		customerRepository.save(customer);
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findByID() {
		log.info("save");
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		assertTrue(customerOptional.isPresent(), "El customer no existe");
				
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("save");
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		assertTrue(customerOptional.isPresent(), "El customer no existe");
		
		Customer customer=customerOptional.get();
		
		customer.setEnable("Y");
		
		customerRepository.save(customer);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		log.info("save");
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		assertTrue(customerOptional.isPresent(), "El customer no existe");
		
		Customer customer=customerOptional.get();
		
		customerRepository.delete(customer);
	}

	@Test
	@Transactional
	@Order(5)
	void findAll() {
		
		//tradicional
		List<Customer> customers=customerRepository.findAll();
		for (Customer customer : customers) {
			log.info("name: " + customer.getName());
			log.info("email: " + customer.getEmail());
		}
		
		//funcional
		customerRepository.findAll().forEach(customer->{
			log.info("name: " + customer.getName());
			log.info("email: " + customer.getEmail());
		});
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		//retorna el total de customers
		log.info("count: " + customerRepository.count());
		
	}
	@Test
	@Transactional
	@Order(7)
	void findByEnableAndEmail() {
		//retorna el total de customers
	 List<Customer> customers=customerRepository.findByEnableAndEmail("Y", "gian_c13@hotmail.com");
	 assertFalse(customers.isEmpty());
	 customers.forEach(customer->{
		 log.info("email: " + customer.getEmail());
		 log.info("name: " + customer.getName());
	 });
	}
	
	@Test
	@Transactional
	@Order(8)
	void findCustomerLikemar() {
		//retorna el total de customers
	 List<Customer> customers=customerRepository.findCustomerLikemar();
	 assertFalse(customers.isEmpty());
	 customers.forEach(customer->{
		 log.info("email: " + customer.getEmail());
		 log.info("name: " + customer.getName());
	 });
	}
}
