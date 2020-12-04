package co.edu.usbcali.demo.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class CartDTO {
	
	@NotNull
	@Positive
	private Integer carId;
	
	@NotNull
	@Size(min = 3, max = 255)
	@NotEmpty
	private String proId;
	
	@NotNull
	@Positive
	private Integer quantity;

	public CartDTO() {
		super();
	}

	public CartDTO(Integer carId, String proId, Integer quantity) {
		super();
		this.carId = carId;
		this.proId = proId;
		this.quantity = quantity;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}