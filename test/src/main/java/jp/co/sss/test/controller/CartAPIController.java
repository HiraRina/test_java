package jp.co.sss.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.test.bean.CartBean;
import jp.co.sss.test.dto.CartSummary;
import jp.co.sss.test.service.CartService;

@RestController
public class CartAPIController {
	
    private final CartService cartService;

    @Autowired
    public CartAPIController(CartService cartService) {
        this.cartService = cartService;
    }
    
	@RequestMapping(path = "/api/cartdetail/{cartId}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProducts(@PathVariable Integer cartId) {
		try {
			cartService.deleteById(cartId);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("削除失敗しました");
		}
	}
	
	@RequestMapping(path = "/api/cartdetail/{cartId}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuantity(
			@PathVariable Integer cartId, 
			@RequestBody Map<String, Integer> request) {
		try {
			Integer newQuantity = request.get("quantity");
			
			CartSummary summary = cartService.updateCartQuantity(cartId,newQuantity);
			
			CartBean targetCart = summary.findCartBeanById(cartId);
			
		    Map<String, Object> response = new HashMap<>();
		    response.put("subtotal", targetCart.getSubtotal());
		    response.put("taxSubtotal", targetCart.getTaxSubtotal());
		    response.put("totalPrice", summary.getTotalPrice());
		    response.put("totalTaxPrice", summary.getTotalTaxPrice());
		    response.put("totalQuantity", summary.getTotalQuantity());
		    
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	    	e.printStackTrace(); // ⭐️ これを追加する！
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数量変更失敗");
	    }
	}

}
