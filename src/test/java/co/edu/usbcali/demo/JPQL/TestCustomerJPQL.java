package co.edu.usbcali.demo.JPQL;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
class TestCustomerJPQL {

	private final static Logger log=LoggerFactory.getLogger(TestCustomerJPQL.class);
	
	@Autowired
	EntityManager entityManager;
	
	@BeforeEach
	public void beforeEach() {
		log.info("beforeEach");
		assertNotNull(entityManager, "el entityManager es nulo");
	}
	
	@Test
	void selectWhereParam() {
		log.info("selectAll");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.enable=:enable AND cus.email=:email";
		List<Customer> customers=entityManager.
				createQuery(jqpl, Customer.class).
				setParameter("enable", "Y").
				setParameter("email", "fgiraudot0@economist.com").
				getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
		
		
//		for (Customer customer : customers) {
//			log.info(customer.getEmail());
//			log.info(customer.getName());
//		}
	}
	
	@Test
	void selectWhereEnable() {
		log.info("selectAll");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.enable='Y'";
		List<Customer> customers=entityManager.createQuery(jqpl, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
		
		
//		for (Customer customer : customers) {
//			log.info(customer.getEmail());
//			log.info(customer.getName());
//		}
	}
	
	
	@Test
	void selectLike() {
		log.info("selectAll");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.name LIKE 'Mar%'";
		List<Customer> customers=entityManager.createQuery(jqpl, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
		
		
//		for (Customer customer : customers) {
//			log.info(customer.getEmail());
//			log.info(customer.getName());
//		}
	}
	
	
	@Test
	void selectAll() {
		log.info("selectAll");
		String jqpl="SELECT cus FROM Customer cus";
		List<Customer> customers=entityManager.createQuery(jqpl, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
		
		
//		for (Customer customer : customers) {
//			log.info(customer.getEmail());
//			log.info(customer.getName());
//		}
	}


}
