package co.edu.usbcali.demo.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public class ShoppingCartDTO {
	
	/*@NotNull
	@Positive*/
	private Integer carId;
	
	@NotNull
	@Email
	@Size(min = 3, max = 255)
	private String email;
	
	/*@NotNull
	@Size(min = 4, max = 255)
	@NotEmpty
	private String cusName;
	
	@NotNull
	@Size(min = 6, max = 255)
	@NotEmpty
	private String cusPhone;
	
	@NotNull
	@Size(min = 3, max = 255)
	@NotEmpty
	private String cusAddress;*/
	
	@NotNull
	@Positive
	private Integer payId;
	
	/*@NotNull
	@Size(min = 1, max = 255)
	@NotEmpty
	private String payName;*/
	
	@NotNull
	@Positive
	private Integer items;

	@NotNull
	@Positive
	private Long total;
	
	//private List<String> shoppingProducts;
	
	@NotNull
	@Size(min = 1, max = 1)
	@NotEmpty
	private String enable;
	

	public ShoppingCartDTO() {
		super();
	}

	public ShoppingCartDTO(
			Integer carId, String email,
			/*String cusName, String cusPhone,
			String cusAddress,*/ Integer payId,
			/*String payName,*/ Integer items,
			Long total, String enable
			) {
		super();
		this.carId = carId;
		this.email = email;
		/*this.cusName = cusName;
		this.cusPhone = cusPhone;
		this.cusAddress = cusAddress;*/
		this.payId = payId;
		//this.payName = payName;
		this.items = items;
		this.total = total;
		this.enable = enable;
		//this.shoppingProducts = shoppingProducts;
	}
	
	/*public List<String> getShoppingProducts() {
		return shoppingProducts;
	}
	public void setShoppingProducts(List<String> shoppingProducts) {
		this.shoppingProducts = shoppingProducts;
	}*/

	/*public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}*/

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}	
}