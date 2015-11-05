package com.core;

import java.util.IllegalFormatFlagsException;

public enum ProductName {
    CHEESE,
    MILK,
    POTATO,
    FISH,
    MEAT;

    public static ProductName createProductName(String name){
        for (ProductName productName : ProductName.values()){
            if (productName.name().equals(name))
                return productName;
        }
        throw new IllegalFormatFlagsException(name);
    }
}