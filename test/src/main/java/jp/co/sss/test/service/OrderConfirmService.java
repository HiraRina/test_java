package jp.co.sss.test.service;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.co.sss.test.bean.CartBean;
import jp.co.sss.test.entity.Cart;
import jp.co.sss.test.form.orderDetailForm;


@Service
public class OrderConfirmService {
	
    private final CartService cartService;
	
    @Autowired
    public OrderConfirmService(CartService cartService) {
        this.cartService = cartService;
    }

	
	//入力された住所の取得
	public String getSelectedAddress(orderDetailForm form, String selectedRadio) {
		
		String selectedAddress = "";

	if("radio1".equals(selectedRadio)) {
		//DBに住所登録がされている場合と新しく入力する場合
		if(form.getUserAddress() != null && !form.getUserAddress().isEmpty()) {
			selectedAddress = form.getUserAddress();
		} else {
			selectedAddress = form.getNewAddress1();
		}
	} else if("radio2".equals(selectedRadio)) {
		selectedAddress = form.getNewAddress2();
		
	} else if("radio3".equals(selectedRadio)) {
		selectedAddress = form.getNewAddress3();
		}
	
	return selectedAddress;
	}
	
	//入力されたアパート名の取得
	public String getSelectedApartment(orderDetailForm form, String selectedRadio) {
		
		String selectedApartment = "";


	if("radio1".equals(selectedRadio)) {
		//DBに住所登録がされている場合と新しく入力する場合
		if(form.getUserAddress() != null && !form.getUserAddress().isEmpty()) {
			selectedApartment = form.getUserApartment();
		} else {
			selectedApartment = form.getNewApartment1();
		}
	} else if("radio2".equals(selectedRadio)) {
		selectedApartment = form.getNewApartment2();
		
	} else if("radio3".equals(selectedRadio)) {
		selectedApartment = form.getNewApartment3();
		}
	
    System.out.println("selectedRadio: " + selectedRadio);
    System.out.println("selectedApartment: " + selectedApartment);

    
    return selectedApartment;
	}

	//購入する商品の情報取得
	public List<CartBean> loadCartItems(HttpSession session,Model model){
		
		List<CartBean> cartItems;
		
		if(session.getAttribute("cartItem") == null) {
			//カート詳細画面から遷移した時
			List<CartBean> cartDetail = cartService.cartDetail(session);
		    cartItems = cartDetail;
			
			//空だった場合
			if (cartDetail.isEmpty()) {
		        model.addAttribute("errorMessage", "カートが空です");
		        return null;
		    }

//	        // カート内合計数を計算(使用するか検討)
//	        int totalquantity = cartDetail.stream()
//	            .mapToInt(CartBean::getQuantity)
//	            .sum();
//	        session.setAttribute("totalquantity", totalquantity);
	        
		} else {
			//単品購入・カート追加からレジへ遷移した時
			//購入品情報
			Cart cartItem = (Cart)session.getAttribute("cartItem");
			
			CartBean sessionItem = new CartBean();
			sessionItem.setProduct(cartItem.getProduct());
			sessionItem.setQuantity(cartItem.getQuantity());
			sessionItem.setSubtotal(cartItem.getProduct().getPrice() * cartItem.getQuantity());
			sessionItem.setTaxSubtotal(cartItem.getProduct().getTaxPrice() * cartItem.getQuantity());
			
		    cartItems = List.of(sessionItem);
			
		}

		return cartItems;

	}
	
}
