package jp.co.sss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.test.entity.SaleItem;
import jp.co.sss.test.repository.SaleItemRepository;

@Controller
public class TopController {
	
	private final SaleItemRepository saleItemRepository;
	
	@Autowired
	public TopController(SaleItemRepository saleItemRepository) {
	    this.saleItemRepository = saleItemRepository;
	}
	
	@RequestMapping(path = "/top",method = RequestMethod.GET)
	public String top(Model model) {
		List<SaleItem> saleItems = saleItemRepository.findAll();
		
		if(saleItems.isEmpty()) {
			model.addAttribute("noSaleItemsMessage", "現在、セール品はありません。");
		}
		
		model.addAttribute("saleItems",saleItems);
		return "top";
	}
}
