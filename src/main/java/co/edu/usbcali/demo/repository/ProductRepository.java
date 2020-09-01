package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;


public interface ProductRepository extends JpaRepository<Product, String> {
	
	//Usando el igualdad de nombre
	@Query("FROM Product WHERE name= ?1")
	public List<Product> findProductName(String name);
	
	//usando el operado MAYOR QUE
	@Query("FROM Product WHERE price > ?1")
	public List<Product> productPriceGreaterThan(int price);
	
	//busqueda definida sin usar query
	public List<Product> findByDetail(String detail);

	//uso del AND
	@Query("FROM Product WHERE price > ?1 AND enable= ?2")
	public List<Product> findByPriceAndEnable(int price, String enable);
	
	//uso del ORDERBY
	@Query("FROM Product WHERE enable = ?1 ORDER BY price ASC")
	public List<Product> enableAndOrdered(String enable);
	
}
