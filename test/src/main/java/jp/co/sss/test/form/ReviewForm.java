package jp.co.sss.test.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ReviewForm {

	@NotBlank
	private String dummyUserName;
	@NotNull
	private Integer rating;
	@Size(min = 1, max = 300)
	private String comment;
	private MultipartFile reviewImage;
	
	public String getDummyUserName() {
		return dummyUserName;
	}
	public void setDummyUserName(String dummyUserName) {
		this.dummyUserName = dummyUserName;
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
	public MultipartFile getReviewImage() {
		return reviewImage;
	}
	public void setReviewImage(MultipartFile reviewImage) {
		this.reviewImage = reviewImage;
	}
	
}
