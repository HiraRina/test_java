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
@Table(name = "reviews")

public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "productId")
	private Product product;
	@Column
	private Integer rating;
	@Column
	private String comment;
	@Column
	private String dummyUserName;
	@Column
	private String reviewImgPath;
	@Column
	private String createdAt;
	
	
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
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
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDummyUserName() {
		return dummyUserName;
	}
	public void setDummyUserName(String dummyUserName) {
		this.dummyUserName = dummyUserName;
	}
	public String getReviewImgPath() {
		return reviewImgPath;
	}
	public void setReviewImgPath(String reviewImgPath) {
		this.reviewImgPath = reviewImgPath;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


}
