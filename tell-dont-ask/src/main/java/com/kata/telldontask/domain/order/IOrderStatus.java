package com.kata.telldontask.domain.order;

public interface IOrderStatus {

  IOrderStatus approve();

  IOrderStatus reject();

  IOrderStatus ship();
}
