package jp.co.sss.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_products_gen")
	@SequenceGenerator(name = "seq_products_gen" , sequenceName = "seq_products" ,allocationSize = 1)

	private Integer productId;
	@Column
	private String productName;
	@Column
	private Integer price;
	@Column
	private Integer taxPrice;
	@Column
	private Integer stock;
	@Column
	private String comment;
	@Column
	private String imgPath;
	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "companyId")

	private Company company;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")

	private Category category;

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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

	
	

}
