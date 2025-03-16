package jp.co.sss.test.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_items")

public class SaleItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer saleItemId;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "productId")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "companyId")
	private Company company;
	
	@Column
	private String saleName;
	
	@Column
	private String descrption;
	
	@Column
	private Integer discountRate;
	
	@Column
	private String salesImgPath;
	
	@Column
	private LocalDate startMonth;
	
	@Column
	private LocalDate endMonth;

	public Integer getSaleItemId() {
		return saleItemId;
	}

	public void setSaleItemId(Integer saleItemId) {
		this.saleItemId = saleItemId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	public String getSalesImgPath() {
		return salesImgPath;
	}

	public void setSalesImgPath(String salesImgPath) {
		this.salesImgPath = salesImgPath;
	}

	public LocalDate getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(LocalDate startMonth) {
		this.startMonth = startMonth;
	}

	public LocalDate getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(LocalDate endMonth) {
		this.endMonth = endMonth;
	}
	

	
	
}
