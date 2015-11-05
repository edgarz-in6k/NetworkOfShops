package com.deal;

import com.core.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class DealCustomerWithShopTest {
    private Customer customer;
    private Shop shop;
    private Map<ProductName, ProductInfo> buys;
    private Map<ProductName, ProductInfo> result;
    private Deal deal;
    private Transaction transaction;

    @Before
    public void setUp(){
        customer = new Customer("Customer");
        shop = new Shop("Shop");

        deal = new DealCustomerWithShop(customer, shop);

        buys = new HashMap<>();
        result = new HashMap<>();
    }

    @Test
    public void testShopEmpty(){
        buys.put(ProductName.CHEESE, new ProductInfo(15, 2.5));
        buys.put(ProductName.FISH, new ProductInfo(20, 7.2));

        transaction = deal.deal(buys);

        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testBuysEmpty(){
        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 2.5));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 7.2));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(5, 2.5));
        result.put(ProductName.FISH, new ProductInfo(10, 7.2));
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));
        result.clear();
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testProductShopLessBuy(){
        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 2));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 7));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 2));
        buys.put(ProductName.FISH, new ProductInfo(20, 7));

        transaction = deal.deal(buys);

        result.clear();
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.put(ProductName.CHEESE, new ProductInfo(5, 2));
        result.put(ProductName.FISH, new ProductInfo(10, 7));
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testProductShopMoreBuy(){
        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(15, 2));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(20, 7));

        buys.put(ProductName.CHEESE, new ProductInfo(5, 2));
        buys.put(ProductName.FISH, new ProductInfo(10, 7));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(10, 2));
        result.put(ProductName.FISH, new ProductInfo(10, 7));
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(5, 2));
        result.put(ProductName.FISH, new ProductInfo(10, 7));
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testPriceProductShopMoreBuyPrice(){
        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 10));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 15));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 5));
        buys.put(ProductName.FISH, new ProductInfo(40, 10));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(5, 10));
        result.put(ProductName.FISH, new ProductInfo(10, 15));
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testPriceProductShopLessBuyPrice(){
        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 5));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 10));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 10));
        buys.put(ProductName.FISH, new ProductInfo(40, 15));

        transaction = deal.deal(buys);

        result.clear();
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.put(ProductName.CHEESE, new ProductInfo(5, 5));
        result.put(ProductName.FISH, new ProductInfo(10, 10));
        /*System.out.println("|transaction");
        for (Map.Entry<ProductName, ProductInfo> entry : transaction.getProductSets().entrySet()){
            System.out.print(entry.getKey() + " " + entry.getValue().getCount() + " " + entry.getValue().getPrice() + "; ");
        }
        System.out.println("\n|shop");
        for (Map.Entry<ProductName, ProductInfo> entry : shop.getProductSets().entrySet()){
            System.out.print(entry.getKey() + " " + entry.getValue().getCount() + " " + entry.getValue().getPrice() + "; ");
        }
        System.out.println("\n|result");
        for (Map.Entry<ProductName, ProductInfo> entry : result.entrySet()){
            System.out.print(entry.getKey() + " " + entry.getValue().getCount() + " " + entry.getValue().getPrice() + "; ");
        }
        System.out.println("|");*/
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }
}