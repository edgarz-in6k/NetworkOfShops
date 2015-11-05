package com.core;

public class ProductInfo {
    private double price;
    private int count;

    public ProductInfo(int count, double price) {
        this.count = count;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object object){
        ProductInfo pr = ((ProductInfo)object);
        return (pr.count == count) && (pr.price == price);
    }
}
