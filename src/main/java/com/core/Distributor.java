package com.core;

import java.util.Map;

public class Distributor extends Client{
    private Map<ProductName, ProductInfo> productSets;

    public Distributor(String name, Map<ProductName, ProductInfo> productSets) {
        super(name);
        this.productSets = productSets;
    }

    public Map<ProductName, ProductInfo> getProductSets() {
        return productSets;
    }
}
