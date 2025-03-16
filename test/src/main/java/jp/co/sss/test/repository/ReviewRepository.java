package jp.co.sss.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.test.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer>{
	List<Review> findByProduct_productId(Integer productId);
}
