package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ShoppingProductDTO {
	
	private Integer shprId;

	@NotNull
	@Size(min = 3, max = 255)
	@NotEmpty
	private String proId;
	
	@NotNull
	@Size(min = 3, max = 255)
	@NotEmpty
	private String proName;
	
	@NotNull
	@Positive
	private Integer proPrice;
	
	@NotNull
	@Size(min = 10, max = 255)
	@NotEmpty
	private String proImage;
	
	@NotNull
	@Positive
	private Integer carId;
	
	@NotNull
	@Positive
	private Integer quantity;
	
	@NotNull
	@Positive
	private Long total;

	public ShoppingProductDTO() {
		super();
	}

	public ShoppingProductDTO(Integer shprId, String proId,
			Integer carId, Integer quantity, Long total,String proName,
			Integer proPrice, String proImage) {
		super();
		this.shprId = shprId;
		this.proId = proId;
		this.carId = carId;
		this.quantity = quantity;
		this.total = total;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proImage = proImage;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getProPrice() {
		return proPrice;
	}

	public void setProPrice(Integer proPrice) {
		this.proPrice = proPrice;
	}

	public String getProImage() {
		return proImage;
	}

	public void setProImage(String proImage) {
		this.proImage = proImage;
	}

	public Integer getShprId() {
		return shprId;
	}

	public void setShprId(Integer shprId) {
		this.shprId = shprId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	

}