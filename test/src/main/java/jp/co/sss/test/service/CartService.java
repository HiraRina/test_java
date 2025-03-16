package jp.co.sss.test.service;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.test.bean.CartBean;
import jp.co.sss.test.dto.CartSummary;
import jp.co.sss.test.entity.Cart;
import jp.co.sss.test.entity.Product;
import jp.co.sss.test.entity.User;
import jp.co.sss.test.form.CartForm;
import jp.co.sss.test.repository.CartRepository;
import jp.co.sss.test.repository.ProductRepository;



@Service
public class CartService {
	
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    //カート追加
	public Cart addCart(CartForm form,HttpSession session) {

		//セッションからloginUserを取得
		User loginUser = (User) session.getAttribute("loginUser");

	    // 今回カート追加情報のみセッションに保存（レジ確認用）
		Cart cartItem = new Cart();
		cartItem.setUser(loginUser);		
	    cartItem.setQuantity(form.getQuantity());
	    
		//productIdでProductを取得
		Product product = productRepository.findById(form.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found for Id:" + form.getProductId()));
		cartItem.setProduct(product);
		
		session.setAttribute("cartItem", cartItem); 
		
		return saveCart(loginUser,cartItem);
				
	}
	
	//カート保存
	public Cart saveCart(User loginUser, Cart cartItem) {
		//ユーザーが同じ商品をカートに入れているかどうか検索
		Cart existingCart = cartRepository.findByUser_UserIdAndProduct_ProductId(
				loginUser.getUserId(),cartItem.getProduct().getProductId()
				);
		
		//上記の検索を結果を使用して登録・更新処理
		if(existingCart != null) {
			//ユーザーが既に同じ商品をカートに入れている場合は数量を更新
			existingCart.setQuantity(existingCart.getQuantity() + cartItem.getQuantity());
			cartRepository.save(existingCart);
		} else {
			//カート内に同じ商品が存在しない場合
			cartRepository.save(cartItem);
		}

		//表示させるのは今回カート追加情報なのでcartItemを返す
		return cartItem;
	
	}
	
    // 合計金額の計算（カート内の単品合計）
    public int calculateTotalPrice(Cart savedCart) {
        return savedCart.getProduct().getPrice() * savedCart.getQuantity();
    }
	
    //カート詳細画面
	public  List<CartBean> cartDetail(HttpSession session) {
		
		User loginUser = (User) session.getAttribute("loginUser");
		List<Cart> cartList = cartRepository.findByUser_userId(loginUser.getUserId());

		
		return cartList.stream().map(cartEntity -> {
			CartBean cartBean = new CartBean();
			cartBean.setCartId(cartEntity.getCartId());
			cartBean.setProduct(cartEntity.getProduct());
			cartBean.setPrice(cartEntity.getProduct().getPrice());
			cartBean.setQuantity(cartEntity.getQuantity());
			
	           // 小計を計算
            int subtotal = cartEntity.getProduct().getPrice() * cartEntity.getQuantity();
            cartBean.setSubtotal(subtotal);

            // 税込み小計を計算
            int taxSubtotal = cartEntity.getProduct().getTaxPrice() * cartEntity.getQuantity();
            cartBean.setTaxSubtotal(taxSubtotal);
	        return cartBean;
	        
		}).toList();
		
	}
	
	//カート内の情報で必要な計算
	public CartSummary createCartSummary(HttpSession session) {
		List<CartBean> cartDetails = cartDetail(session);
		
		// カート内合計金額を計算
        int totalPrice = cartDetails.stream()
            .mapToInt(CartBean::getSubtotal)
            .sum();
		
        // カート内税込合計金額を計算
        int totalTaxPrice = cartDetails.stream()
            .mapToInt(CartBean::getTaxSubtotal)
            .sum();
        
        // カート内合計数を計算
        int totalquantity = cartDetails.stream()
            .mapToInt(CartBean::getQuantity)
            .sum();
        
        return new CartSummary(cartDetails, totalPrice, totalTaxPrice, totalquantity);
	}
	
	//カート削除機能
	public void deleteById(Integer cartId) {
		cartRepository.deleteById(cartId);
	}


	public CartSummary updateCartQuantity(Integer cartId,Integer newQuantity) {
	
		Cart cart = cartRepository.findById(cartId)
		    .orElseThrow(() -> new RuntimeException("カートが見つかりません"));

		//編集した商品の数量を懇親して、金額計算
		cart.setQuantity(newQuantity);
		cartRepository.save(cart);
    
		List<Cart> cartList = cartRepository.findByUser_userId(cart.getUser().getUserId());

		List<CartBean> updatedCartBeans = cartList.stream().map(cartEntity -> {
			CartBean cartBean = new CartBean();
			cartBean.setCartId(cartEntity.getCartId());
			cartBean.setProduct(cartEntity.getProduct());
			cartBean.setPrice(cartEntity.getProduct().getPrice());
			cartBean.setQuantity(cartEntity.getQuantity());
		
           // 小計を計算
			int subtotal = cartEntity.getProduct().getPrice() * cartEntity.getQuantity();
			cartBean.setSubtotal(subtotal);

			// 税込み小計を計算
			int taxSubtotal = cartEntity.getProduct().getTaxPrice() * cartEntity.getQuantity();
			cartBean.setTaxSubtotal(taxSubtotal);
			return cartBean;
			}).toList();
	
		int totalPrice = updatedCartBeans.stream().mapToInt(CartBean::getSubtotal).sum();
		int totalTaxPrice = updatedCartBeans.stream().mapToInt(CartBean::getTaxSubtotal).sum();
		int totalQuantity = updatedCartBeans.stream().mapToInt(CartBean::getQuantity).sum();

		return new CartSummary(updatedCartBeans, totalPrice, totalTaxPrice, totalQuantity);
    
}

}