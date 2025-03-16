package jp.co.sss.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.test.bean.ProductBean;
import jp.co.sss.test.bean.ReviewBean;
import jp.co.sss.test.entity.Product;
import jp.co.sss.test.entity.Review;
import jp.co.sss.test.repository.ProductRepository;
import jp.co.sss.test.repository.ReviewRepository;
import jp.co.sss.test.service.ProductService;
import jp.co.sss.test.service.ReviewService;

@Controller
public class ProductDetailController {

	private final ProductService productService;
	private final ReviewService reviewService;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    
    @Autowired
    public ProductDetailController(
    		ProductService productService,
    		ReviewService reviewService,
    		ProductRepository productRepository,
    		ReviewRepository reviewRepository) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @RequestMapping(path = "/productDetail/{productId}", method = RequestMethod.GET)
	public String showProduct(@PathVariable int productId, Model model) {
    	Optional<Product> optionalProduct = productRepository.findById(productId);
 
    	if(optionalProduct.isEmpty()) {
    		return "redirect:/error";
    	}

    	Product productEntity = optionalProduct.get();
    	ProductBean productBean = productService.convertToProductBean(productEntity);
		model.addAttribute("product", productBean);

		List<Review> reviews = reviewRepository.findByProduct_productId(productId);
		List<ReviewBean> reviewBeans = reviewService.mapToReviewBeans(reviews);
		model.addAttribute("reviews",reviewBeans);

		model.addAttribute("showSidebar", true);
		model.addAttribute("addCart", true);
		model.addAttribute("cartDetail", false);
		model.addAttribute("quantity", 1); // 初期値として数量を設定

		return "product_detail";
	}
}
