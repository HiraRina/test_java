package jp.co.sss.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.test.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{
}
