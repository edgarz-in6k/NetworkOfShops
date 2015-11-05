package com.core;

import java.util.HashMap;

public class TransactionByDistributors extends Transaction<Distributor> {

    public TransactionByDistributors(Distributor distributor, Shop shop) {
        this(distributor, shop, new HashMap<>());
    }

    public TransactionByDistributors(Distributor distributor, Shop shop, HashMap<ProductName, ProductInfo> productSets) {
        this.t = distributor;
        this.shop = shop;
        this.productSets = productSets;
    }

    public Distributor getDistributor(){
        return t;
    }
}