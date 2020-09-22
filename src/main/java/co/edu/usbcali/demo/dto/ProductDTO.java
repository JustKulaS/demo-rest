package co.edu.usbcali.demo.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public class ProductDTO {

	private String proId;
	private String detail;
	private String enable;
	private String image;
	private String name;
	private int price;	
	//private List<ShoppingProduct> shoppingProducts = new ArrayList<ShoppingProduct>(0);
	
	
	public ProductDTO() {
		super();
	}


	public ProductDTO(String proId, String detail, String enable, String image, String name, int price,
			List<ShoppingProduct> shoppingProducts) {
		super();
		this.proId = proId;
		this.detail = detail;
		this.enable = enable;
		this.image = image;
		this.name = name;
		this.price = price;
		//this.shoppingProducts = shoppingProducts;
	}


	public String getProId() {
		return proId;
	}


	public void setProId(String proId) {
		this.proId = proId;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getEnable() {
		return enable;
	}


	public void setEnable(String enable) {
		this.enable = enable;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	/*public List<ShoppingProduct> getShoppingProducts() {
		return shoppingProducts;
	}


	public void setShoppingProducts(List<ShoppingProduct> shoppingProducts) {
		this.shoppingProducts = shoppingProducts;
	}*/

	
}
