package com.kata.telldontask.domain.repository;

import com.kata.telldontask.domain.Product;
import com.kata.telldontask.domain.order.exception.UnknownProduct;

public interface ProductCatalog {
    Product getByName(String name) throws UnknownProduct;
}
