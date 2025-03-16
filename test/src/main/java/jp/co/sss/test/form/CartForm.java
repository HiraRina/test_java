package jp.co.sss.test.form;

import jakarta.validation.constraints.NotBlank;

public class CartForm {
	@NotBlank
	private Integer quantity;
	@NotBlank
	private Integer productId;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	

	
}
