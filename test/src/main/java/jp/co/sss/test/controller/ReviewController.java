package jp.co.sss.test.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sss.test.form.ReviewForm;
import jp.co.sss.test.service.ReviewService;

@Controller
public class ReviewController {

	private final ReviewService reviewService;
	
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
	
	@RequestMapping(path = "/review/{productId}",method = RequestMethod.GET)
	public String review(@PathVariable int productId, Model model) {
		model.addAttribute("showSidebar", false);
		//リクエストパラメータからproductIdを渡す
		model.addAttribute("product",productId);
		model.addAttribute("reviewForm", new ReviewForm());
		return "review";
	}
	
	@RequestMapping(path = "/addReview/{productId}",method = RequestMethod.POST)
	public String addReview(
			@PathVariable int productId,
			@Valid @ModelAttribute ReviewForm form,
			BindingResult result,
			Model model,
			HttpSession session,
			@RequestParam(value="reviewImage") MultipartFile imageFile) {
		System.out.println("Form data received: " + form.getDummyUserName() + ", " + form.getRating() + ", " + form.getComment());

		if(result.hasErrors()) {
	        model.addAttribute("productId", productId);
	        model.addAttribute("reviewForm", form);
			return "review";
		}
		
		try {
			reviewService.addReview(productId,form,session,imageFile);
			return "redirect:/productList";
		} catch(RuntimeException e) {
			model.addAttribute("error", "口コミ投稿に失敗しました。");
			return "review";
			}
	}	
}
