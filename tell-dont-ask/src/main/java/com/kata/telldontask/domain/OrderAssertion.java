package com.kata.telldontask.domain;

import com.kata.telldontask.domain.exception.ApprovedOrderCannotBeRejected;
import com.kata.telldontask.domain.exception.RejectedOrderCannotBeApproved;
import com.kata.telldontask.domain.exception.ShippedOrdersCannotBeChanged;

public class OrderAssertion {

    /**
     * Invariant
     * @param order
     */
    public static void assertNotRejected(Order order) {
        if (order.getStatus().equals(OrderStatus.REJECTED)) {
            throw new RejectedOrderCannotBeApproved();
        }
    }

    /**
     * Invariant
     * @param order
     */
    public static void assertNotShipped(Order order) {
        if (order.getStatus().equals(OrderStatus.SHIPPED)) {
            throw new ShippedOrdersCannotBeChanged();
        }
    }

    /**
     * Invariant
     * @param order
     */
    public static void assertNotApproved(Order order) {
        if (order.getStatus().equals(OrderStatus.APPROVED)) {
            throw new ApprovedOrderCannotBeRejected();
        }
    }
}
