package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import co.edu.usbcali.demo.domain.Product;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductServiceTest {

	private final static  Logger log=LoggerFactory.getLogger(CustomerServiceTest.class);
	
	private final static String proId="APPL4454";
	
	@Autowired
	ProductService productService;
	
	
	@Test
	@Order(1)
	void save()throws Exception {
		log.info("save");
		Optional<Product> productOptional=productService.findById(proId);
		assertFalse(productOptional.isPresent(), "El producto ya existe");
		
		Product product = new Product();
		product.setProId(proId);
		product.setName("Android XYASDA");
		product.setPrice(4900000);
		product.setDetail("nueva G");
		product.setImage("estaimagen.com");
		product.setEnable("N");

		productService.save(product);
	}
	
	@Test
	@Order(2)
	void findByID() throws Exception {
		log.info("findbyID");
		Optional<Product> productOptional=productService.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
				
	}
	
	@Test
	@Order(3)
	void update() throws Exception {
		log.info("update");
		Optional<Product> productOptional=productService.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
		
		Product product=productOptional.get();
		
		product.setEnable("Y");
		
		productService.update(product);
	}
	
	@Test
	@Order(4)
	void delete() throws Exception {
		log.info("delete");
		Optional<Product> productOptional=productService.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
		
		Product product=productOptional.get();
		
		productService.delete(product);
	}


}
