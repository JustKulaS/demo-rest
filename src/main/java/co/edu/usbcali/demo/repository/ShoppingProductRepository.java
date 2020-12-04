package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Integer> {
	
	@Query("SELECT SUM(shpr.total) FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public Long totalShoppingProductByShoppingCart(Integer carId);

	
	@Query("SELECT shoppingPro FROM ShoppingProduct shoppingPro WHERE shoppingPro.shoppingCart.carId= ?1 AND shoppingPro.product.proId= ?2 ")
	public List<ShoppingProduct> findproId(Integer carId, String proId);
	
	@Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId = ?1")
	public List<ShoppingProduct> findByCarId(Integer carId);
}

