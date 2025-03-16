package jp.co.sss.test.service;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import jp.co.sss.test.bean.CartBean;
import jp.co.sss.test.dto.OrderDTO;
import jp.co.sss.test.entity.Cart;
import jp.co.sss.test.entity.Order;
import jp.co.sss.test.entity.OrderItem;
import jp.co.sss.test.entity.User;
import jp.co.sss.test.repository.CartRepository;
import jp.co.sss.test.repository.OrderItemRepository;
import jp.co.sss.test.repository.OrderRepository;
import jp.co.sss.test.repository.UserRepository;

@Service
public class OrderCompleteService {
	
	private final OrderRepository orderRepository;

	private final OrderItemRepository orderItemRepository;

	private final UserRepository userRepository;
	
	private final CartRepository cartRepository;
	
    public OrderCompleteService(
    	OrderRepository orderRepository,
    	OrderItemRepository orderItemRepository,
    	UserRepository userRepository,
    	CartRepository cartRepository
    	) {
        	this.orderRepository = orderRepository;
        	this.orderItemRepository = orderItemRepository;
        	this.userRepository = userRepository;
        	this.cartRepository = cartRepository;
    }
	
	@Transactional
	public void createOrder(HttpSession session) {
	
	//セッションからloginUserを取得
	User loginUser = (User) session.getAttribute("loginUser");
	
	//セッションから購入データを取得
	List<CartBean> orderItems = (List<CartBean>) session.getAttribute("orderItems");
	String selectedAddress = (String) session.getAttribute("selectedAddress");
	String selectedApartment = (String) session.getAttribute("selectedApartment");
	Integer totalAmount = (Integer) session.getAttribute("totalTaxPrice");

    if (orderItems == null || orderItems.isEmpty()) {
        throw new IllegalStateException("注文情報がありません");
    }

    //orderテーブルにデータ保存
    Order order = saveOrder(loginUser,totalAmount);
    
    //order_itemsテーブルにデータを保存
    saveOrderItems(order, orderItems);

    //住所をUserテーブルに保存
    updateUserAddress(loginUser, selectedAddress, selectedApartment);
    
    //ユーザーのカート情報を取得、更新
    updateCart(loginUser, orderItems);
    
    // セッションのクリア
    session.removeAttribute("orderItems");
    session.removeAttribute("totalTaxPrice");
    session.removeAttribute("selectedAddress");
    session.removeAttribute("selectedApartment");
    
    session.setAttribute("orderId", order.getOrderId());
    
	}
	
	//orderテーブル保存
	public Order saveOrder(User loginUser, Integer totalAmount) {
		Order order = new Order();
	    order.setUser(loginUser);
	    order.setTotalAmount(totalAmount);
	    order.setStatus("pending");
	    order = orderRepository.save(order);
	    return order;
	}
	
	//orderItemテーブル保存
	public void saveOrderItems(Order order, List<CartBean> orderItems) {
	    for(CartBean item : orderItems) {
	    	OrderItem orderItem = new OrderItem();
	    	orderItem.setOrder(order);
	    	orderItem.setProduct(item.getProduct());
	    	orderItem.setQuantity(item.getQuantity());
	    	orderItem.setPrice(item.getTaxSubtotal());
	        orderItemRepository.save(orderItem);
	    }
	}
	
	//ユーザーの住所の登録・更新
	public void updateUserAddress(User loginUser, String selectedAddress, String selectedApartment) {
	    if(selectedAddress != null && selectedApartment != null) {
	    	String fullAddress = selectedAddress + " " + selectedApartment;
	    	loginUser.setUserAddress(fullAddress);
	    	userRepository.save(loginUser);
	    }
	}
	
    //ユーザーのカート情報を取得し、購入した分を更新。0になったらカートをDBから削除
	public void updateCart(User loginUser, List<CartBean> orderItems) {
	    for(CartBean orderItem : orderItems) {
	    	Cart cartItem = cartRepository.findByUser_UserIdAndProduct_ProductId(loginUser.getUserId(), orderItem.getProduct().getProductId());
	    	if(cartItem != null) {
	    		int updatedQuantity = cartItem.getQuantity() - orderItem.getQuantity();
	    			if(updatedQuantity <= 0) {
	    				cartRepository.delete(cartItem);
	    			} else {
	    				cartItem.setQuantity(updatedQuantity);
	    				cartRepository.save(cartItem);
	    			}
	    	}
	    }
	}
	
	
	//注文完了画面にて他の情報も表示するためにDTO作成している
	@Transactional
	public OrderDTO getOrderById(Integer orderId) {
		Order order = orderRepository.findById(orderId)
		.orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
		
		return new OrderDTO(order);
		
	}

}
