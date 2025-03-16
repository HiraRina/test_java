package jp.co.sss.test.dto;

import jp.co.sss.test.entity.OrderItem;

public class OrderItemDTO {

	
	private Integer orderItemId;
	private String productName;
	private String imgPath; 
	private Integer quantity;
	private Integer unitPrice;
	private Integer price;
	
	public OrderItemDTO(OrderItem orderItem) {
		this.orderItemId = orderItem.getOrderItemId();
		this.productName = orderItem.getProduct().getProductName();
		this.imgPath = orderItem.getProduct().getImgPath();
		this.quantity = orderItem.getQuantity();
		this.price = orderItem.getPrice();
        // 合計金額を計算してセット
        this.unitPrice = orderItem.getProduct().getTaxPrice();
	}
	
	public Integer getOrderItemId() { return orderItemId; }
	public String getProductName() { return productName; }
	public String getImgPath() { return imgPath; }
	public Integer getQuantity() { return quantity; }
	public Integer getPrice() { return price; }
	public Integer getUnitPrice() { return unitPrice; }

	
}
