package jp.co.sss.test.bean;

import java.io.Serializable;

public class ReviewBean implements Serializable{

	private Integer reviewId;
	private Integer userId;
	private String userName;
	private Integer productId;
	private Integer rating;
	private String comment;
	private String post;
	private String dummyUserName;
	private String reviewImgPath;
	private String createdAt;

	
	
	public ReviewBean() {
		
	}


	public Integer getReviewId() {
		return reviewId;
	}


	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
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


	public String getPost() {
		return post;
	}


	public void setPost(String post) {
		this.post = post;
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


	public void setReviewImgPath(String revieeImgPath) {
		this.reviewImgPath = revieeImgPath;
	}


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
