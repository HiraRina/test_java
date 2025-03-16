package jp.co.sss.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.test.bean.ProductBean;
import jp.co.sss.test.entity.Product;
import jp.co.sss.test.form.SearchForm;
import jp.co.sss.test.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	//検索機能
	public List<ProductBean> searchProducts(SearchForm form){
		List<Product> products = new ArrayList<>();
		
		//商品名・カテゴリーの入力をチェックし、それに合わせて検索
		if(form.getProductName() != null && !form.getProductName().isEmpty()) {
			products = form.getCategoryId() == null
				? productRepository.findByProductNameContaining(form.getProductName())
				: productRepository.findByProductNameContainingAndCategoryCategoryId(form.getProductName(),form.getCategoryId());
		} else {
			products = form.getCategoryId() != null
				? productRepository.findByCategoryCategoryId(form.getCategoryId())
				: productRepository.findAll();
		}
			return mapToProductBeans(products);
	}
	
	//商品一覧画面時、１つずつProductBeanへ変換処理
	public List<ProductBean> mapToProductBeans(List<Product> products){
		return products.stream().map(productEntity -> {
			ProductBean productBean = new ProductBean();
			BeanUtils.copyProperties(productEntity,productBean);
			
	   		//メーカー名をセット
    		productBean.setCompanyName(productEntity.getCompany().getCompanyName());

    		return productBean;
		}).toList();
	}
	
	//商品詳細画面、ProductBeanへの変換処理
	public ProductBean convertToProductBean(Product product) {
	   	ProductBean productBean = new ProductBean();
    	BeanUtils.copyProperties(product, productBean);
    	
		//メーカー名をセット
		productBean.setCompanyName(product.getCompany().getCompanyName());
		
		return productBean;

	}
	
	
	
}
