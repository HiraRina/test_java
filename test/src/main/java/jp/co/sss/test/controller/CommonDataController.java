package jp.co.sss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sss.test.entity.Category;
import jp.co.sss.test.repository.CategoryRepository;

@ControllerAdvice
public class CommonDataController {

	@Autowired
	private CategoryRepository categoryRepository;

	//検索機能で使用するカテゴリー情報取得
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryRepository.findAll();
	}

	//サイドバーの非表示(デフォルトでfalse)
	@ModelAttribute("showSidebar")
	public boolean getShowSidedbar() {
		return false;
	}
}
