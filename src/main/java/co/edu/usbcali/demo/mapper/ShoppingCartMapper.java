package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;

@Mapper
public interface ShoppingCartMapper {
	
	@Mapping(source = "customer.email", target = "email")
	/*@Mapping(source = "customer.name", target = "cusName")
	@Mapping(source = "customer.phone", target = "cusPhone")
	@Mapping(source = "customer.address", target = "cusAddress")*/
	@Mapping(source = "paymentMethod.payId", target = "payId")
	//@Mapping(source = "paymentMethod.name", target = "payName")
	public ShoppingCartDTO toShoppingCartDTO(ShoppingCart shoppingCart);
	
	@Mapping(source = "email", target = "customer.email")
	@Mapping(source = "payId", target = "paymentMethod.payId")
	public ShoppingCart toShoppingCart(ShoppingCartDTO shoppingCartDTO);
	
	public List<ShoppingCartDTO> toShoppingCartsDTO(List<ShoppingCart> shoppingCart);
	
	public List<ShoppingCart> toShoppingCarts(List<ShoppingCartDTO> shoppingCartDTO);

}