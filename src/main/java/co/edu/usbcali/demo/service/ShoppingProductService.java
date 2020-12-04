package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductService extends GenericService<ShoppingProduct, Integer> {

	Long totalShoppingProductByShoppingCart(Integer carId) throws Exception;

	List<ShoppingProduct> findShoppingProductProId(Integer carId, String proId) throws Exception;
	
	List<ShoppingProduct> findByCarId(Integer carId) throws Exception;
}
