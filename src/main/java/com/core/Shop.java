package com.core;

import java.util.HashMap;
import java.util.Map;

public class Shop {
    private String name;
    private Map<ProductName, ProductInfo> productSets;

    public Shop(String name) {
        this(name, new HashMap<>());
    }

    public Shop(String name, HashMap<ProductName, ProductInfo> productSets) {
        this.name = name;
        this.productSets = productSets;
    }

    public String getName(){
        return name;
    }

    void setName(String name){
        this.name = name;
    }

    public Map<ProductName, ProductInfo> getProductSets() {
        return productSets;
    }

    public void setProductSets(Map<ProductName, ProductInfo> productSets) {
        this.productSets = productSets;
    }
}
