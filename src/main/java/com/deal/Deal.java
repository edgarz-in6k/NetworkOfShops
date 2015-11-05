package com.deal;

import com.core.*;

import java.util.Map;

public abstract class Deal {
    public Shop shop;

    abstract Transaction deal(Map<ProductName, ProductInfo> productsBuys);
}
