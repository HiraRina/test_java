package jp.co.sss.test.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.test.bean.CartBean;
import jp.co.sss.test.form.orderDetailForm;
import jp.co.sss.test.service.OrderConfirmService;

@Controller
public class OrderConfirmController {


    private final OrderConfirmService orderConfirmService;
    

    @Autowired
    public OrderConfirmController(OrderConfirmService orderConfirmService) {
        this.orderConfirmService = orderConfirmService;
    }
	
	@RequestMapping(path = "/orderConfirm", method = RequestMethod.POST)
	public String orderConfirm(Model model,
			orderDetailForm form,HttpSession session
			) {
		
		//選択されたラジオボタンを取得
		String selectedRadio = form.getAddress();

		//住所、アパート名取得
		String selectedAddress = orderConfirmService.getSelectedAddress(form, selectedRadio);
		String selectedApartment = orderConfirmService.getSelectedApartment(form, selectedRadio);

		model.addAttribute("selectedRadio",selectedRadio);
		session.setAttribute("selectedAddress",selectedAddress);
		session.setAttribute("selectedApartment",selectedApartment);

		List<CartBean> cartItems = orderConfirmService.loadCartItems(session,model);
		
		session.setAttribute("orderItems", cartItems);
		
		int totalTaxPrice = cartItems.stream().mapToInt(CartBean::getTaxSubtotal).sum();
		session.setAttribute("totalTaxPrice",totalTaxPrice);
		
		
		return "order_confirm";
	}
}
