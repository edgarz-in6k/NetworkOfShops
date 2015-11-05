package com.core;

import java.util.Map;

public abstract class Transaction<T> {
    protected Map<ProductName, ProductInfo> productSets;
    protected Shop shop;
    protected T t;

    public Map<ProductName, ProductInfo> getProductSets() {
        return productSets;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}