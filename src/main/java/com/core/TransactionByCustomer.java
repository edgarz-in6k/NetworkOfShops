package com.core;

import com.output.AboutTransaction;

import java.util.*;

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

    public static ArrayList<TransactionByCustomer> toArrayList(List list){
        ArrayList<TransactionByCustomer> transactionByCustomers = new ArrayList<>();

        List<AboutTransaction> aboutTransactions = new ArrayList<>();
        for (Object object : list){
            Object[] objects = (Object[])object;
            aboutTransactions.add(new AboutTransaction(
                    objects[0].toString(),
                    objects[1].toString(),
                    objects[2].toString(),
                    objects[3].toString(),
                    objects[4].toString(),
                    objects[5].toString(),
                    objects[6].toString(),
                    objects[7].toString()));
        }

        while (!aboutTransactions.isEmpty()){
            AboutTransaction about = aboutTransactions.remove(0);
            TransactionByCustomer transactionByCustomer = new TransactionByCustomer(new Customer(about.getNameCustomer()), new Shop(about.getNameShop()));

            transactionByCustomer.getProductSets().put(
                            ProductName.createProductName(about.getNameProduct()),
                            new ProductInfo(Integer.parseInt(about.getCount()), Double.parseDouble(about.getPrice())));
            getProducts(aboutTransactions, about.getId(), transactionByCustomer.getProductSets());

        }

        return transactionByCustomers;
    }

    private static Map<ProductName, ProductInfo> getProducts(List<AboutTransaction> aboutTransactions, String id, Map<ProductName, ProductInfo> productSets) {
        Map<ProductName, ProductInfo> map = new HashMap<>();
        while (!aboutTransactions.isEmpty() && aboutTransactions.get(0).getId().equals(id)){
            AboutTransaction about = aboutTransactions.remove(0);
            productSets.put(
                    ProductName.createProductName(about.getNameProduct()),
                    new ProductInfo(Integer.parseInt(about.getCount()), Double.parseDouble(about.getPrice()))
            );
        }
        return map;
    }
}
