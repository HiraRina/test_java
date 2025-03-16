package jp.co.sss.test.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")

public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private User user;
	
	@Column
	private Integer totalAmount;
	
	@Column
	private String status;
	
	//完了画面の確認時に商品の情報も一緒に取得できるように定義
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
	

}
