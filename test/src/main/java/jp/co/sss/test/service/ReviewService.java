package jp.co.sss.test.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sss.test.bean.ReviewBean;
import jp.co.sss.test.entity.Product;
import jp.co.sss.test.entity.Review;
import jp.co.sss.test.entity.User;
import jp.co.sss.test.form.ReviewForm;
import jp.co.sss.test.repository.ReviewRepository;

@Service
public class ReviewService {
	
	
	private final ReviewRepository reviewRepository;
	private final ImageService imageService;
	
	public ReviewService(ReviewRepository reviewRepository,ImageService imageService) {
		this.reviewRepository = reviewRepository;
		this.imageService = imageService;
	}
	

	public List<ReviewBean> mapToReviewBeans(List<Review> reviews){
		return reviews.stream().map(reviewEntity -> {
			ReviewBean reviewBean = new ReviewBean();
			BeanUtils.copyProperties(reviewEntity, reviewBean);
			
			reviewBean.setUserName(reviewEntity.getUser().getUserName());
			
			return reviewBean;
		}).toList();
	}
	
	//レビューDB登録
	public void addReview(int productId, ReviewForm form,HttpSession session,MultipartFile imageFile) {
		Review review = createReviewFromForm(productId,form,session,imageFile);
			reviewRepository.save(review);
		}

	//登録するレビュー情報整理
	private Review createReviewFromForm(int productId, ReviewForm form,HttpSession session,MultipartFile imageFile) {
		Review review = new Review();
		BeanUtils.copyProperties(form, review ,"reviewId");

		//セッションからloginUserを取得
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser != null) {
		//userIdのみを取得
		User user = new User();
		user.setUserId(loginUser.getUserId());
		review.setUser(user);
		}
		
		//リクエストパラメータからproductIdを取得
		Product product = new Product();
		product.setProductId(productId);
		review.setProduct(product);
		
		//画像の情報を取得
		if(imageFile != null && !imageFile.isEmpty()) {
			String savePath = imageService.createImageFile(imageFile);
			review.setReviewImgPath(savePath);
		} else {
            review.setReviewImgPath(null);
        }
		
		//投稿日時の登録
		review.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		return review;

	}

	
}
