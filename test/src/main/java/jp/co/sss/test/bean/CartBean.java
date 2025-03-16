package jp.co.sss.test.bean;

import java.io.Serializable;

import jp.co.sss.test.entity.Product;
import jp.co.sss.test.entity.User;

public class CartBean implements Serializable{

	private Integer cartId;
	private User user;
	private Product product;
	private Integer quantity;
	private Integer price;
	private Integer subtotal;
	private Integer taxSubtotal;
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getTaxSubtotal() {
		return taxSubtotal;
	}
	public void setTaxSubtotal(Integer taxSubtotal) {
		this.taxSubtotal = taxSubtotal;
	}

	
	

}
