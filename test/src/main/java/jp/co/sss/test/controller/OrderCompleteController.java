package jp.co.sss.test.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.test.dto.OrderDTO;
import jp.co.sss.test.repository.OrderRepository;
import jp.co.sss.test.service.OrderCompleteService;

@Controller
public class OrderCompleteController {


	private final OrderCompleteService orderCompleteService;
	private final OrderRepository orderRepository;

    @Autowired
    public OrderCompleteController(OrderCompleteService orderCompleteService, OrderRepository orderRepository) {
        this.orderCompleteService = orderCompleteService;
        this.orderRepository = orderRepository;
    }
	
	
	
	@RequestMapping(path = "/orderItems",method = RequestMethod.POST)
	public String createOrder(Model model,HttpSession session) {
		
		orderCompleteService.createOrder(session);
 
		return "redirect:/orderComplete";
		
	}
	
	@RequestMapping(path = "/orderComplete",method = RequestMethod.GET)
	@Transactional
	public String showOrderComplete(Model model,HttpSession session) {
		
		Integer orderId = (Integer) session.getAttribute("orderId");
		
		// 直接アクセスを防ぐため
	    if (orderId == null) {
	        return "redirect:/productList"; 
	    }
	    
	    //今回DBに保存した注文情報を取得
	    OrderDTO orderDTO = orderCompleteService.getOrderById(orderId);
	    model.addAttribute("order",orderDTO);
	    model.addAttribute("orderItems", orderDTO.getOrderItems());
	 
	    session.removeAttribute("orderId");
 
		return "show_orderItems";
	}
}
