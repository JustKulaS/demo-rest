package co.edu.usbcali.demo.repository;

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
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;



@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingProductRepositoryTest {

	private final static  Logger log=LoggerFactory.getLogger(ShoppingCartRepositoryTest.class);
	
	private static Integer shprId=null;
	
	private static String proId = "APPL45";
	
	private static Integer carId=1;
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	ShoppingProductRepository shoppingProductRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		ShoppingProduct shoppingProduct = new ShoppingProduct();
		shoppingProduct.setShprId(shprId);
		shoppingProduct.setQuantity(7);
		shoppingProduct.setTotal(450000L);
		
		Optional<Product> productOptional = productRepository.findById(proId);
		assertTrue(productOptional.isPresent(),"el producto con id: " + proId + "no existe");
		
		Product product = productOptional.get();
		shoppingProduct.setProduct(product);
		
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(),"el carro de compras con id:" + carId + "no existe");
		
		ShoppingCart shoppingCart = shoppingCartOptional.get();
		shoppingProduct.setShoppingCart(shoppingCart);
		
		shoppingProduct = shoppingProductRepository.save(shoppingProduct);
		
		shprId = shoppingProduct.getShprId();
		
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById(){
		Optional<ShoppingProduct> shoppingProductOptional=shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El producto de compra con carid: "+shprId + "no existe");
		
	}
	
	@Test
	@Transactional
	@Order(3)
	void update(){
		Optional<ShoppingProduct> shoppingProductOptional=shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El producto de compra con carid: "+carId + "no existe");
		
		ShoppingProduct shoppingProduct=shoppingProductOptional.get();
		shoppingProduct.setQuantity(20);
		
		shoppingProductRepository.save(shoppingProduct);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete(){
		Optional<ShoppingProduct> shoppingProductOptional=shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El producto de compra con carid: "+carId + "no existe");
		
		ShoppingProduct shoppingProduct=shoppingProductOptional.get();

		shoppingProductRepository.delete(shoppingProduct);
	}	
	

}
