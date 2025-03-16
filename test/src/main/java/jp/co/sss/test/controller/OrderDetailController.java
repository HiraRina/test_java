package jp.co.sss.test.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.test.dto.AddressInfoDTO;
import jp.co.sss.test.entity.Cart;
import jp.co.sss.test.entity.Product;
import jp.co.sss.test.entity.User;
import jp.co.sss.test.repository.ProductRepository;
import jp.co.sss.test.service.OrderDetailService;

@Controller
public class OrderDetailController {

	private final ProductRepository productRepository;
	private final OrderDetailService orderDetailService;
	
    @Autowired
    public OrderDetailController(ProductRepository productRepository, OrderDetailService orderDetailService) {
        this.productRepository = productRepository;
        this.orderDetailService = orderDetailService;
    }

	//単品購入から購入品詳細画面へ遷移
	@RequestMapping(path = "/singleOrder",method = RequestMethod.GET)
	public String singleOrder(Model model,
			@RequestParam("productId") int productId,
			@RequestParam(value = "quantity", defaultValue = "1") int quantity,
			HttpSession session) {
		Cart cartItem = new Cart();
		
		//productIdでProductを取得
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not "
						+ "found for Id:" + productId));
				cartItem.setProduct(product);

			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			
		session.setAttribute("cartItem",cartItem);
		System.out.println("Cart Item Saved to Session: " + cartItem);

		return "redirect:/orderDetail";
	}
	
	
	//カート追加画面から購入品詳細画面へ遷移(今カートに入れたもののみ購入)
		@RequestMapping(path = "/cartOrder",method = RequestMethod.GET)
		public String cartOrder(Model model,
				HttpSession session) {
			
//			Cart cartItem = (Cart) session.getAttribute("cartItem");
			
			return "redirect:/orderDetail";
		}
		
		//カート詳細画面から購入品詳細画面へ遷移(現在カートに入っているもの全て購入)
		@RequestMapping(path = "/cartDetailOrder",method = RequestMethod.GET)
		public String cartDetailOrder(Model model,
				HttpSession session) {

			return "redirect:/orderDetail";
		}
	
	
	@RequestMapping(path = "/orderDetail",method = RequestMethod.GET)
		public String orderDetail(
		        @RequestParam(value = "selectedRadio", required = false) String selectedRadio,
		        Model model,HttpSession session) {
		
		User loginUser = (User) session.getAttribute("loginUser");
		
	    //初回遷移時はradio1を選択する
	    if(selectedRadio == null) {
	    	selectedRadio = "radio1";
	    }

		//sessionからデータを取得(初回遷移時はnull、編集時は入力情報保持) 
		String selectedAddress = (String) session.getAttribute("selectedAddress");
		String selectedApartment = (String) session.getAttribute("selectedApartment");
		System.out.println("selectedAddress: " + selectedAddress);

	    AddressInfoDTO addressInfo = orderDetailService.createAddressInfo(
	    	loginUser,selectedRadio,selectedAddress,selectedApartment
	    );
		
		System.out.println("selectedRadio: " + selectedRadio);
	    model.addAttribute("selectedRadio", selectedRadio);
	    model.addAttribute("addressInfo", addressInfo);

		return "order_detail";
	}
	

	
	
	
}

