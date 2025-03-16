package jp.co.sss.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.test.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{

}
