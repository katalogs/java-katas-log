package com.kata.telldontask.domain.repository;

import com.kata.telldontask.domain.Order;

public interface OrderRepository {
    void save(Order order);

    Order getById(int orderId);
}
