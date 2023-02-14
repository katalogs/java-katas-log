package com.kata.telldontask.domain.order;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.order.exception.*;

import static com.kata.telldontask.domain.order.OrderStatus.*;

public class OrderAssertion {

     
    public static void assertNotRejected(Order order) {
        if (order.getStatus().equals(OrderStatus.REJECTED)) {
            throw new RejectedOrderCannotBeApproved();
        }
    }

    public static void assertNotShipped(Order order) {
        if (order.getStatus().equals(OrderStatus.SHIPPED)) {
            throw new ShippedOrdersCannotBeChanged();
        }
    }


    public static void assertNotApproved(Order order) {
        if (order.getStatus().equals(OrderStatus.APPROVED)) {
            throw new ApprovedOrderCannotBeRejected();
        }
    }

    public static void assertNotCreatedOrRejected(Order order) {
        if (order.getStatus().equals(CREATED) || order.getStatus().equals(REJECTED)) {
            throw new OrderCannotBeShipped();
        }
    }

    public static void assertNotAlreadyShipped(Order order) {
        if (order.getStatus().equals(SHIPPED)) {
            throw new OrderCannotBeShippedTwice();
        }
    }
}
