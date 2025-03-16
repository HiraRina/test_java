package jp.co.sss.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.test.bean.CartBean;
import jp.co.sss.test.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{
	List<Cart> findByUser_userId(Integer userId);
	Cart findByUser_UserIdAndProduct_ProductId(Integer userId,Integer productId);
	CartBean findByCartId(Integer cartId);
}
