package jp.co.sss.test.dto;

import java.util.List;

import jp.co.sss.test.bean.CartBean;

public class CartSummary {

	private List<CartBean> cartDetails;
	private int totalPrice;
	private int totalTaxPrice;
	private int totalQuantity;
	
	public CartSummary(List<CartBean> cartDetails,int totalPrice,int totalTaxPrice,int totalQuantity) {
		this.cartDetails = cartDetails;
		this.totalPrice = totalPrice;
		this.totalTaxPrice = totalTaxPrice;
		this.totalQuantity = totalQuantity;
	}
	
	public List<CartBean> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartBean> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalTaxPrice() {
		return totalTaxPrice;
	}

	public void setTotalTaxPrice(int totalTaxPrice) {
		this.totalTaxPrice = totalTaxPrice;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
	/**
	 * 編集時にcartId を指定して CartBean を取得する
	 * 
	 * @param cartId 編集対象のカートID
	 * @return 該当する CartBean
	 */
	
	public CartBean findCartBeanById(Integer cartId) {
		return cartDetails.stream()
			.filter(cart -> cart.getCartId().equals(cartId))
			.findFirst()
			.orElseThrow(() -> new RuntimeException("該当するカートが見つかりません"));
	}
}