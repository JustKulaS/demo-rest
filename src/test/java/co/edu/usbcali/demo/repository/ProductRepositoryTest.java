package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductRepositoryTest {

private final static  Logger log=LoggerFactory.getLogger(CustomerRepositoryTest.class);
	
	private final static String proId = "HMZ0";
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("save");
		Optional<Product> productOptional=productRepository.findById(proId);
		assertFalse(productOptional.isPresent(), "El producto ya existe");
		
		Product product = new Product();
		product.setProId(proId);
		product.setName("Android XY");
		product.setPrice(4900000);
		product.setDetail("nueva G");
		product.setImage("estaimagen.com");
		product.setEnable("N");

		productRepository.save(product);
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findByID() {
		log.info("findbyID");
		Optional<Product> productOptional=productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
				
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("update");
		Optional<Product> productOptional=productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
		
		Product product=productOptional.get();
		
		product.setEnable("Y");
		
		productRepository.save(product);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		log.info("delete");
		Optional<Product> productOptional=productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
		
		Product product=productOptional.get();
		
		productRepository.delete(product);
	}

	@Test
	@Transactional
	@Order(5)
	void findAll() {
		log.info("findAll");
		//tradicional
		List<Product> products=productRepository.findAll();
		for (Product product : products) {
			log.info("name: " + product.getName());
			log.info("email: " + product.getPrice());
		}
		
		//funcional
		/*productRepository.findAll().forEach(product->{
			log.info("name: " + product.getName());
			log.info("email: " + product.getPrice());
		});*/
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		//retorna el total de productos
		log.info("count: " + productRepository.count());
		
	}
	
	@Test
	@Transactional
	@Order(7)
	void findProductName(){
		List<Product> products = productRepository.findProductName("iPhone X");
		 assertFalse(products.isEmpty());
		 products.forEach(product->{
			 log.info("nombre: " + product.getName());
			 log.info("precio: " + product.getPrice());
		 });
	}


	@Test
	@Transactional
	@Order(8)
	void findPriceGreaterThan() {
		List<Product> products = productRepository.productPriceGreaterThan(4470000);
		 assertFalse(products.isEmpty());
		 products.forEach(product->{
			 log.info("nombre: " + product.getName());
			 log.info("precio: " + product.getPrice());
		 });
	}
	@Test
	@Transactional
	@Order(9)
	void findByDetail() {
		List<Product> products = productRepository.findByDetail("iPad Pro");
		 assertFalse(products.isEmpty());
		 products.forEach(product->{
			 log.info("nombre: " + product.getName());
			 log.info("precio: " + product.getPrice());
		 });
	}
	@Test
	@Transactional
	@Order(10)
	void findByPriceAndEnable() {
		List<Product> products = productRepository.findByPriceAndEnable(4500000, "Y");
		 assertFalse(products.isEmpty());
		 products.forEach(product->{
			 log.info("nombre: " + product.getName());
			 log.info("precio: " + product.getPrice());
			 log.info("Enable: " + product.getEnable());
		 });
	}
	@Test
	@Transactional
	@Order(11)
	void orderedEnable() {
		List<Product> products = productRepository.enableAndOrdered("Y");
		 assertFalse(products.isEmpty());
		 products.forEach(product->{
			 log.info("nombre: " + product.getName());
			 log.info("precio: " + product.getPrice());
			 log.info("Enable: " + product.getEnable());
		 });	
	}
}
