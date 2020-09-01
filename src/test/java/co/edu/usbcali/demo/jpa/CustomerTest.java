package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
class CustomerTest {

	private final static String email = "ftapmapas@gmail.com";

	private final static Logger log = LoggerFactory.getLogger(CustomerTest.class);
	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	@Order(1)
	void save() {
		// siga sino es nulo
		assertNotNull(entityManager, "el entity es nulo");
		Customer customer = entityManager.find(Customer.class, email);

		assertNull(customer, "EL cliente con email: " + email + " existe");

		customer = new Customer();
		customer.setAddress("lo que sea 123");
		customer.setEmail(email);
		customer.setEnable("y");
		customer.setName("gian");
		customer.setPhone("1213 131 41");
		customer.setToken("NKASJDKAD34353KJ");

		entityManager.persist(customer);
	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		assertNotNull(entityManager, "el entity es nulo");
		Customer customer = entityManager.find(Customer.class, email);

		assertNotNull(entityManager, "el entity es nulo");
		
		log.info(customer.getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		assertNotNull(entityManager, "el entity es nulo");
		Customer customer = entityManager.find(Customer.class, email);

		assertNotNull(entityManager, "el entity es nulo");
		
		customer.setEnable("N");
		entityManager.merge(customer);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		assertNotNull(entityManager, "el entity es nulo");
		Customer customer = entityManager.find(Customer.class, email);

		assertNotNull(entityManager, "el entity es nulo");
		
		entityManager.remove(customer);
	}
}
