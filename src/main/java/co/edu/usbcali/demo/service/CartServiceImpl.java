package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@Service
@Scope("singleton")
public class CartServiceImpl implements CartService {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ShoppingProductService shoppingProductService;
	
	@Autowired
	ProductService productService;
	
	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
	public ShoppingCart createCart(String email) throws Exception {
		Customer customer=null;
		if(email==null || email.isBlank()==true) {
			throw new Exception("El email del cliente es nulo");
		}
		
		Optional<Customer> customerOptional=customerService.findById(email);
		if(customerOptional.isPresent()==false) {
			throw new Exception("No existe un customer con el email: "+ email);
		}
		
		customer=customerOptional.get();
		if(customer.getEnable()==null || customer.getEnable().equals("N")==true) {
			throw new Exception("El cliente con email: "+ email + "no esta habilitado");
		}
		
		ShoppingCart shoppingCart=new ShoppingCart(0, customer, null,0,0L,"Y",null);
		
		shoppingCart=shoppingCartService.save(shoppingCart);
		return shoppingCart;
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingProduct addProduct(Integer carId, String proId, Integer quantity) throws Exception {
		
		ShoppingCart shoppingCart = null;
		Product product = null;
		Long total = 0L;
		Long totalCart = 0L;
		
		if(carId == null || carId < 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}
		if(proId == null || proId.isBlank()) {
			throw new Exception("el proId es nulo o vacio");
		}
		if(quantity == null || quantity < 0) {
			throw new Exception("La cantidad es nulo o menor a cero");
		}
		
		if(shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("No existe el carId: "+carId);
		}
		
		shoppingCart = shoppingCartService.findById(carId).get();
		
		if(shoppingCart.getEnable().equals("N")) {
			throw new Exception("ShoppingCart esta inhabilitado");
		}
		
		if(productService.findById(proId).isPresent() == false) {
			throw new Exception("El product no existe");
		}
		
		product = productService.findById(proId).get();
		if(product.getEnable().equals("N")) {
			throw new Exception("El producto esta inhabilitado");
		}
		
		total = Long.valueOf(product.getPrice() * quantity);
		
		ShoppingProduct shoppingProduct = new ShoppingProduct();
		
		// Si el product ya existe en el cart
		// ---> No se crea uno nuevo, se aumenta la cantidad
		if(shoppingProductService.findShoppingProductProId(carId, proId).isEmpty()) {
			shoppingProduct.setProduct(product);
			shoppingProduct.setQuantity(quantity);
			shoppingProduct.setShoppingCart(shoppingCart);
			shoppingProduct.setShprId(0);
			shoppingProduct.setTotal(total);
		} else {
			shoppingProduct = shoppingProductService.findShoppingProductProId(carId, proId).get(0);
			shoppingProduct.setQuantity(shoppingProduct.getQuantity() + quantity);
			shoppingProduct.setTotal(shoppingProduct.getTotal() + total);
		}		
		
		shoppingProduct = shoppingProductService.save(shoppingProduct);
		
		totalCart = shoppingProductService.totalShoppingProductByShoppingCart(carId);
		
		shoppingCart.setTotal(totalCart);
		shoppingCart.setItems(shoppingCart.getItems() + quantity);
		shoppingCartService.update(shoppingCart);
		
		return shoppingProduct;
	}

	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
	public void removeProduct(Integer carId, String proId) throws Exception {
		ShoppingProduct shoppingProduct =null;
		ShoppingCart shoppingCart = null;
		Product product = null;
		
	
		if(carId == null || carId < 0 ) {
			throw new Exception("el car Id esta vacio o no es valido");
		}
		
		if(shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("no existe el carrito de compra con id: " + carId);	
		}
		
		if(proId == null || proId.isBlank()) {
			throw new Exception("el id del producto esta vacio o no es valido");
		}
		
		shoppingCart = shoppingCartService.findById(carId).get();
		
		if(shoppingCart.getEnable().equals("N")) {
			throw new Exception("ShoppingCart esta inhabilitado");
			}
		
		
		if(productService.findById(proId).isPresent() == false) {
			throw new Exception("El product no existe");
		}
		
		product = productService.findById(proId).get();
		if(product.getEnable().equals("N")) {
			throw new Exception("El producto esta inhabilitado");
		}
		
		shoppingProduct = shoppingProductService.findShoppingProductProId(carId, proId).get(0);		
		if(shoppingProduct.getQuantity() > 1) {
			shoppingProduct.setQuantity(shoppingProduct.getQuantity() - 1);
			shoppingProduct.setTotal(shoppingProduct.getTotal() - product.getPrice());
		}else {
			shoppingProductService.deleteById(shoppingProduct.getShprId());
		}
		shoppingCart.setTotal(shoppingCart.getTotal() - product.getPrice());
		shoppingCart.setItems(shoppingCart.getItems() - 1);

	}

	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
	public void clearCart(Integer carId) throws Exception {
		ShoppingCart shoppingCart = null;
		List<ShoppingProduct> shoppingProducts = null;
		
		if(carId == null || carId < 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}
		
		if(shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("No existe el carrito de compra con carId: "+carId);
		}
		
		shoppingCart = shoppingCartService.findById(carId).get();
		
		if(shoppingCart.getEnable().equals("N")) {
			throw new Exception("ShoppingCart esta desactivado");
		}
		if(shoppingCart.getItems() == 0) {
			throw new Exception("no hay productos");
		}
		
		shoppingProducts = shoppingProductService.findByCarId(carId);
		
		for (ShoppingProduct shoppingProduct : shoppingProducts) {
			shoppingProductService.delete(shoppingProduct);
		}
		
		shoppingCart.setItems(0);
		shoppingCart.setTotal(0L);
		shoppingCartService.update(shoppingCart);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId) throws Exception {
		return shoppingProductService.findByCarId(carId);
	}

}
