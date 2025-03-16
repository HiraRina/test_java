package jp.co.sss.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.test.entity.SaleItem;

public interface SaleItemRepository  extends JpaRepository<SaleItem,Integer>{

}
