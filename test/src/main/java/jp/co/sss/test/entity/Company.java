package jp.co.sss.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")


public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer companyId;
	@Column
	private String companyName;
	@Column
	private String streetAddress;
	@Column
	private String representativeName;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	
	
	
	
	
}
