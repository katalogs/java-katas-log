package com.kata.telldontask.repository;

import com.kata.telldontask.domain.Product;

public interface ProductCatalog {
    Product getByName(String name);
}
