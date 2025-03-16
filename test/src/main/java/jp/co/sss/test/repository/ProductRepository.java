package jp.co.sss.test.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.test.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Integer>{
    List<Product> findByProductNameContaining(String productName);
	List<Product> findByCategoryCategoryId(Integer categoryId);
	List<Product> findByProductNameContainingAndCategoryCategoryId(String productName,Integer categoryId);
	
}
