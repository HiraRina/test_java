package jp.co.sss.test.bean;

import java.io.Serializable;


public class ProductBean implements Serializable{

	private Integer productId;
	private String productName;
	private Integer price;
	private Integer taxPrice;
	private Integer stock;
	private String comment;
	private String imgPath;
	private Integer companyId;
	private String companyName;
	private Integer categoryId;
	private String categoryName;
	
	public ProductBean() {
		
	}


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Integer taxPrice) {
		this.taxPrice = taxPrice;
	}


	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
}
