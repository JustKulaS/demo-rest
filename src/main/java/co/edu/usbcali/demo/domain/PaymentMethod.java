package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "payment_method", schema = "public")
public class PaymentMethod implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer payId;
	
	@NotEmpty
	@Size(min = 1, max = 1)
	private String enable;
	
	@NotEmpty
	@Size(min = 4, max = 255)
	private String name;
	
	private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>(0);

	public PaymentMethod() {
	}

	public PaymentMethod(Integer payId, String enable, String name, List<ShoppingCart> shoppingCarts) {
		this.payId = payId;
		this.enable = enable;
		this.name = name;
		this.shoppingCarts = shoppingCarts;
	}

	@Id
	@Column(name = "pay_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentMethod")
	public List<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
}
