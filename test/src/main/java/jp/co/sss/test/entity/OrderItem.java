package jp.co.sss.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")

public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderItemId;
	
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "orderId")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "productId")
	private Product product;

	@Column
	private Integer quantity;
	
	@Column
	private Integer price;

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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


	
	

}
