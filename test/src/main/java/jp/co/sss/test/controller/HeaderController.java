package jp.co.sss.test.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.test.bean.ProductBean;
import jp.co.sss.test.form.SearchForm;
import jp.co.sss.test.repository.ProductRepository;
import jp.co.sss.test.service.ProductService;

@Controller
public class HeaderController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public HeaderController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }
	

	
	@RequestMapping(path = "/mypage", method = RequestMethod.GET)
	public String mypage(HttpSession session) {
		return"mypage";
	}
	
	@RequestMapping(path = "/logout" , method = RequestMethod.GET)
	public String logout(HttpSession session) {
		if(session != null) {
			session.removeAttribute("loginUser");
			//セッション全体を無効化
			session.invalidate();
		}
		return "redirect:/login";	
	}

	@RequestMapping(path = "/productList", method = RequestMethod.POST)
	public String search(@ModelAttribute SearchForm form,Model model) {
		
		List<ProductBean> productBeans = productService.searchProducts(form);
		model.addAttribute("products",productBeans);
		return "products_list";
	}
}
