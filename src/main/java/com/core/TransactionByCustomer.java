package com.core;

import java.util.HashMap;
import java.util.Map;

public class TransactionByCustomer extends Transaction<Customer> {

    public TransactionByCustomer(Customer customer, Shop shop) {
        this(customer, shop, new HashMap<>());
    }

    public TransactionByCustomer(Customer customer, Shop shop, Map<ProductName, ProductInfo> productSets) {
        this.t = customer;
        this.shop = shop;
        this.productSets = productSets;
    }

    public Customer getCustomer(){
        return t;
    }
}
