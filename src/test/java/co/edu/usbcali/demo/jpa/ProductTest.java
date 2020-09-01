package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductTest {

	private final static String proId = "AAPZ50";

	private static final Logger log = LoggerFactory.getLogger(ProductTest.class);

	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	@Order(1)
	void save() {
		assertNotNull(entityManager, "el entity es nulo");

		Product product = entityManager.find(Product.class, proId);

		assertNull(product, "El producto con: " + proId + " existe");

		product = new Product();
		product.setProId(proId);
		product.setName("Iphone X");
		product.setPrice(4500000);
		product.setDetail("nueva generacion");
		product.setImage("estaimagen.com");
		product.setEnable("y");

		entityManager.persist(product);
	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		assertNotNull(entityManager, "el entity es nulo");
		Product product = entityManager.find(Product.class, proId);

		assertNotNull(entityManager, "el entity es nulo");
		
		log.info(product.getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		assertNotNull(entityManager, "el entity es nulo");
		Product product = entityManager.find(Product.class, proId);

		assertNotNull(entityManager, "el entity es nulo");
		
		product.setEnable("N");
		entityManager.merge(product);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		assertNotNull(entityManager, "el entity es nulo");
		Product product = entityManager.find(Product.class, proId);

		assertNotNull(entityManager, "el entity es nulo");
		
		entityManager.remove(product);
	}
}

