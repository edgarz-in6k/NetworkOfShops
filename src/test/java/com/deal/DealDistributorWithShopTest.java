package com.deal;

import com.core.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class DealDistributorWithShopTest {
    private Distributor distributor;
    private Shop shop;
    private Map<ProductName, ProductInfo> buys;
    private Map<ProductName, ProductInfo> result;
    private Deal deal;
    private Transaction transaction;

    @Before
    public void setUp(){
        distributor = new Distributor("Distributor", new HashMap<>());
        shop = new Shop("Shop");

        deal = new DealDistributorWithShop(distributor, shop);

        buys = new HashMap<>();
        result = new HashMap<>();
    }

    @Test
    public void testShopEmpty(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(100, 2.5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(150, 7.2));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 2.5));
        buys.put(ProductName.FISH, new ProductInfo(20, 7.2));

        result.put(ProductName.CHEESE, new ProductInfo(15, 2.5));
        result.put(ProductName.FISH, new ProductInfo(20, 7.2));

        transaction = deal.deal(buys);

        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testDistributorEmpty(){
        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 2.5));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 7.2));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 2.5));
        buys.put(ProductName.FISH, new ProductInfo(20, 7.2));

        transaction = deal.deal(buys);

        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testPriceWithDistributorEmpty(){
        /*distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(100, 5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(150, 10));*/

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 10));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 15));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 5));
        buys.put(ProductName.FISH, new ProductInfo(40, 10));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(5, 10));//(10*5+5*15)/(5+15)
        result.put(ProductName.FISH, new ProductInfo(10, 15));//(15*10+10*40)/(10+40)
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        assertTrue(distributor.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testBuysEmpty(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(100, 2.5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(150, 7.2));

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 2.5));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 7.2));

        transaction = deal.deal(buys);

        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testPriceWithBuysEmpty(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(100, 5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(150, 10));

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 10));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 15));

        /*buys.put(ProductName.CHEESE, new ProductInfo(15, 5));
        buys.put(ProductName.FISH, new ProductInfo(40, 10));*/

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(5, 10));//(10*5+5*15)/(5+15)
        result.put(ProductName.FISH, new ProductInfo(10, 15));//(15*10+10*40)/(10+40)
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(100, 5));
        result.put(ProductName.FISH, new ProductInfo(150, 10));
        assertTrue(distributor.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testProductDistributorMoreBuy(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(100, 2));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(150, 7));

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 2));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 7));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 2));
        buys.put(ProductName.FISH, new ProductInfo(20, 7));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(20, 2));
        result.put(ProductName.FISH, new ProductInfo(30, 7));
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(85, 2));
        result.put(ProductName.FISH, new ProductInfo(130, 7));
        assertTrue(distributor.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(15, 2));
        result.put(ProductName.FISH, new ProductInfo(20, 7));
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testPriceProductDistributorMoreBuyPrice(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(100, 5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(150, 10));

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 10));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 15));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 5));
        buys.put(ProductName.FISH, new ProductInfo(40, 10));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(20, 6.25));//(10*5+5*15)/(5+15)
        result.put(ProductName.FISH, new ProductInfo(50, 11));//(15*10+10*40)/(10+40)
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(85, 5));
        result.put(ProductName.FISH, new ProductInfo(110, 10));
        assertTrue(distributor.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(15, 5));
        result.put(ProductName.FISH, new ProductInfo(40, 10));
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testProductDistributorLessBuy(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(20, 2.5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(30, 7.2));

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 2.5));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 7.2));

        buys.put(ProductName.CHEESE, new ProductInfo(100, 2.5));
        buys.put(ProductName.FISH, new ProductInfo(150, 7.2));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(25, 2.5));
        result.put(ProductName.FISH, new ProductInfo(40, 7.2));
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        assertTrue(distributor.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(20, 2.5));
        result.put(ProductName.FISH, new ProductInfo(30, 7.2));
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testPriceProductDistributorLessBuyPrice(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(15, 5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(40, 10));

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 10));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 15));

        buys.put(ProductName.CHEESE, new ProductInfo(20, 5));
        buys.put(ProductName.FISH, new ProductInfo(50, 10));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(20, 6.25));//(10*5+5*15)/(5+15)
        result.put(ProductName.FISH, new ProductInfo(50, 11));//(15*10+10*40)/(10+40)
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        assertTrue(distributor.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(15, 5));
        result.put(ProductName.FISH, new ProductInfo(40, 10));
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testProductDistributorEqualsShop(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(100, 2.5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(150, 7.2));

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 2.5));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 7.2));

        buys.put(ProductName.CHEESE, new ProductInfo(100, 2.5));
        buys.put(ProductName.FISH, new ProductInfo(150, 7.2));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(105, 2.5));
        result.put(ProductName.FISH, new ProductInfo(160, 7.2));
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        assertTrue(distributor.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(100, 2.5));
        result.put(ProductName.FISH, new ProductInfo(150, 7.2));
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }

    @Test
    public void testPriceProductDistributorEqualsBuyPrice(){
        distributor.getProductSets().put(ProductName.CHEESE, new ProductInfo(15, 5));
        distributor.getProductSets().put(ProductName.FISH, new ProductInfo(40, 10));

        shop.getProductSets().put(ProductName.CHEESE, new ProductInfo(5, 10));
        shop.getProductSets().put(ProductName.FISH, new ProductInfo(10, 15));

        buys.put(ProductName.CHEESE, new ProductInfo(15, 5));
        buys.put(ProductName.FISH, new ProductInfo(40, 10));

        transaction = deal.deal(buys);

        result.put(ProductName.CHEESE, new ProductInfo(20, 6.25));//(10*5+5*15)/(5+15)
        result.put(ProductName.FISH, new ProductInfo(50, 11));//(15*10+10*40)/(10+40)
        assertTrue(shop.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        assertTrue(distributor.getProductSets().entrySet().containsAll(result.entrySet()));

        result.clear();
        result.put(ProductName.CHEESE, new ProductInfo(15, 5));
        result.put(ProductName.FISH, new ProductInfo(40, 10));
        assertTrue(transaction.getProductSets().entrySet().containsAll(result.entrySet()));
    }
}