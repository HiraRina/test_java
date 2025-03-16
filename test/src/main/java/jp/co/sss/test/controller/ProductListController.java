package jp.co.sss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.test.bean.ProductBean;
import jp.co.sss.test.entity.Product;
import jp.co.sss.test.repository.ProductRepository;
import jp.co.sss.test.service.ProductService;

@Controller
public class ProductListController {

	private final ProductService productService;
	private final ProductRepository productRepository;

    @Autowired
    public ProductListController(ProductService productService,ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

	@RequestMapping(path = "/productList",method = RequestMethod.GET)
	public String ploductList(Model model) {
		model.addAttribute("showSidebar", false);

		List<Product> products = productRepository.findAll();
		List<ProductBean> productBeans = productService.mapToProductBeans(products);
		model.addAttribute("products",productBeans);

		return "products_list";
	}
}

