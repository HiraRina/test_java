package jp.co.sss.test.dto;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.sss.test.entity.Order;

public class OrderDTO {

	private Integer orderId;
	private UserDTO user;
	private Integer totalAmount;
	private String status;
	private List<OrderItemDTO> orderItems;
	
	public OrderDTO(Order order) {
		this.orderId = order.getOrderId();
		this.user = new UserDTO(order.getUser());
		this.totalAmount = order.getTotalAmount();
		this.status = order.getStatus();
		this.orderItems = order.getOrderItems().stream()
				.map(OrderItemDTO::new)
                .collect(Collectors.toList());
	}
	
	public Integer getOrderId() { return orderId; }	
	public UserDTO getUser() { return user; }
	public Integer getTotalAmount() { return totalAmount; }
	public String getStatus() { return status; }
	public List<OrderItemDTO> getOrderItems() { return orderItems; }
	
}