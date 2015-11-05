package com.deal;

import com.core.*;

import java.util.Map;

public class DealCustomerWithShop extends Deal {

    private Customer customer;
    private TransactionByCustomer transaction;

    public DealCustomerWithShop(Customer customer, Shop shop) {
        this.customer = customer;
        this.shop = shop;
    }

    @Override
    public TransactionByCustomer deal(Map<ProductName, ProductInfo> productsBuys) {
        transaction = new TransactionByCustomer(customer, shop);
        Map<ProductName, ProductInfo> productsShop = shop.getProductSets();

        for (Map.Entry<ProductName, ProductInfo> buys : productsBuys.entrySet()) {

            ProductName key = buys.getKey();

            if (isShopProduct(buys)) {

                int shopProductCount = shopProductsCount(key);
                int buyProductCount = buyProductsCount(buys);

                if (shopProductCount > buyProductCount) {
                    addingProductTransaction(buys.getValue(), buys.getKey(), buyProductCount);
                    subtractingProductShop(key, buyProductCount);
                } else if (shopProductCount < buyProductCount) {
                    addingProductTransaction(productsShop.get(key), key, shopProductCount);
                    subtractingProductShop(key);
                } else {
                    addingProductTransaction(productsShop.get(key), key, shopProductCount);
                    subtractingProductShop(key);
                }
            }
        }

        return transaction;
    }


    private int transactionProductsCount(ProductName key) {
        if (isContainsValueTransaction(key))
            return transaction.getProductSets().get(key).getCount();
        return 0;
    }

    private int buyProductsCount(Map.Entry<ProductName, ProductInfo> entry) {
        return entry.getValue().getCount();
    }

    private int shopProductsCount(ProductName key) {
        return shop.getProductSets().get(key).getCount();
    }

    private void subtractingProductShop(ProductName key, int diffShopCount) {
        shop.getProductSets().get(key).setCount(shopProductsCount(key) - diffShopCount);
    }

    private void subtractingProductShop(ProductName key) {
        shop.getProductSets().remove(key);
    }

    private void addingProductTransaction(ProductInfo value, ProductName key, int addTransactionProductCount) {
        if (isContainsValueTransaction(key))
            transaction.getProductSets().get(key).setCount(transactionProductsCount(key) + addTransactionProductCount);
        else
            transaction.getProductSets().put(key, value);
    }

    private boolean isShopProduct(Map.Entry<ProductName, ProductInfo> entry) {
        if (shop.getProductSets().containsKey(entry.getKey())) {
            if (shop.getProductSets().get(entry.getKey()).getPrice() <= entry.getValue().getPrice())
                return true;
        }
        return false;
    }

    private boolean isContainsValueTransaction(ProductName key) {
        return transaction.getProductSets().containsKey(key);
    }
}