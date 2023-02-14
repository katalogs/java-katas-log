package com.kata.telldontask.doubles;

import com.kata.telldontask.domain.Product;
import com.kata.telldontask.domain.repository.ProductCatalog;
import com.kata.telldontask.domain.order.exception.UnknownProduct;

import java.util.List;

public class InMemoryProductCatalog implements ProductCatalog {
    private final List<Product> products;

    public InMemoryProductCatalog(List<Product> products) {
        this.products = products;
    }

    public Product getByName(final String name) {
        return products.stream().filter(p -> p.getName().equals(name)).findFirst().orElseThrow(UnknownProduct::new);
    }
}
