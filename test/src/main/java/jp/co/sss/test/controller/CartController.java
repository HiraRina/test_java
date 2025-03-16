package jp.co.sss.test.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.test.dto.CartSummary;
import jp.co.sss.test.entity.Cart;
import jp.co.sss.test.form.CartForm;
import jp.co.sss.test.service.CartService;

@Controller
public class CartController {
	
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
	@RequestMapping(path = "/addToCart",method = RequestMethod.POST)
	public String addToCart(@ModelAttribute CartForm form, Model model,HttpSession session) {

		//カート追加(または更新)と現在カート追加した情報を取得
		Cart savedCart = cartService.addCart(form,session);
		model.addAttribute("addCartProducts", savedCart);
		
	    // 合計計算
	    int totalPrice = cartService.calculateTotalPrice(savedCart);
		model.addAttribute("totalPrice", totalPrice);

		return "add_cart";
	}
	
	@RequestMapping(path = "/cartDetail",method = RequestMethod.GET)
	public String cartDetail(Model model,HttpSession session) {
		
	    // レジに進む際の処理のためセッション内の単品カート情報を削除
	    session.removeAttribute("cartItem");

		model.addAttribute("showSidebar", true);
		model.addAttribute("addCart", false);
		model.addAttribute("cartDetail", true);

		CartSummary summary = cartService.createCartSummary(session);
		
        model.addAttribute("totalPrice", summary.getTotalPrice());
        model.addAttribute("totalTaxPrice", summary.getTotalPrice());
        model.addAttribute("totalquantity", summary.getTotalQuantity());
		model.addAttribute("cartDetail", summary.getCartDetails());

		return "cart_detail";
	}

}
