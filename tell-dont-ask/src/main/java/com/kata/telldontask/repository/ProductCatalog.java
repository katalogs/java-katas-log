package com.kata.telldontask.repository;

import com.kata.telldontask.domain.Product;
import com.kata.telldontask.domain.exception.UnknownProduct;

public interface ProductCatalog {
    Product getByName(String name) throws UnknownProduct;
}
