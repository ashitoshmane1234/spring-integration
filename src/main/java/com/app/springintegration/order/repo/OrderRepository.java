package com.app.springintegration.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.springintegration.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
