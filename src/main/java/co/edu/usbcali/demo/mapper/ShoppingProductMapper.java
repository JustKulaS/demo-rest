package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;

@Mapper
public interface ShoppingProductMapper {
	
	@Mapping(source = "shoppingCart.carId", target = "carId")
	@Mapping(source = "product.proId", target = "proId")
	@Mapping(source = "product.name", target = "proName")
	@Mapping(source = "product.price", target = "proPrice")
	@Mapping(source = "product.image", target = "proImage")
	public ShoppingProductDTO toShoppingProductDTO(ShoppingProduct shoppingProduct);
	
	@Mapping(source = "carId", target = "shoppingCart.carId")
	@Mapping(source = "proId", target = "product.proId")
	public ShoppingProduct toShoppingProduct(ShoppingProductDTO shoppingProductDTO);
	
	public List<ShoppingProductDTO> toShoppingProductsDTO(List<ShoppingProduct> shoppingProducts);
	
	public List<ShoppingProduct> toShoppingProducts(List<ShoppingProductDTO> shoppingProductDTO);

}