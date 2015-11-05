package com.deal;

import com.core.*;

import java.util.Map;

public class DealDistributorWithShop extends Deal {

    private Distributor distributor;
    private TransactionByDistributors transaction;

    public DealDistributorWithShop(Distributor distributor, Shop shop) {
        this.distributor = distributor;
        this.shop = shop;
    }

    @Override
    public Transaction deal(Map<ProductName, ProductInfo> buys) {

        transaction = new TransactionByDistributors(distributor, shop);
        Map<ProductName, ProductInfo> productsDistributor = distributor.getProductSets();

        for (Map.Entry<ProductName, ProductInfo> entry : buys.entrySet()) {

            ProductName key = entry.getKey();

            if (isDistributorProduct(key)) {

                int distributorProductCount = distributorProductsCount(key);
                int buyProductCount = buyProductsCount(entry);

                if (distributorProductCount > buyProductCount) {
                    addingProductTransaction(entry.getValue(), entry.getKey(), buyProductCount);
                    addingProductShop(buys, entry, buyProductCount);
                    subtractingProductDistributor(key, buyProductCount);
                } else {
                    addingProductTransaction(productsDistributor.get(key), key, distributorProductCount);
                    addingProductShop(productsDistributor, entry, distributorProductCount);
                    subtractingProductDistributor(key);
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
        if (isContainsValueShop(key))
            return shop.getProductSets().get(key).getCount();
        return 0;
    }

    private int distributorProductsCount(ProductName key) {
        return distributor.getProductSets().get(key).getCount();
    }

    private void subtractingProductDistributor(ProductName key, int diffDistributorCount) {
        distributor.getProductSets().get(key).setCount(distributorProductsCount(key) - diffDistributorCount);
    }

    private void subtractingProductDistributor(ProductName key) {
        distributor.getProductSets().remove(key);
    }

    private void addingProductTransaction(ProductInfo value, ProductName key, int addTransactionProductCount) {
        if (isContainsValueTransaction(key))
            transaction.getProductSets().get(key).setCount(transactionProductsCount(key) + addTransactionProductCount);
        else
            transaction.getProductSets().put(key, value);
    }

    private void addingProductShop(Map<ProductName, ProductInfo> productSets, Map.Entry<ProductName,
            ProductInfo> entry, int addShopProductCount) {
        if (isContainsValueShop(entry.getKey())) {
            if (isPriceDifferent(entry))
                calculatePrice(productSets, entry.getKey());
            shop.getProductSets().get(entry.getKey()).setCount(shopProductsCount(entry.getKey()) + addShopProductCount);
        } else
            shop.getProductSets().put(entry.getKey(), productSets.get(entry.getKey()));
    }

    private void calculatePrice(Map<ProductName, ProductInfo> productSets, ProductName key) {
        double priceShop = shop.getProductSets().get(key).getPrice();
        double priceBuy = productSets.get(key).getPrice();
        double countShop = shop.getProductSets().get(key).getCount();
        double countBuy = productSets.get(key).getCount();
        double sumCounts = countShop + countBuy;
        double resultPrice = (priceShop * countShop + priceBuy * countBuy) / sumCounts;
        shop.getProductSets().get(key).setPrice(resultPrice);
    }

    private boolean isDistributorProduct(ProductName key) {
        return distributor.getProductSets().containsKey(key);
    }

    private boolean isContainsValueShop(ProductName key) {
        return shop.getProductSets().containsKey(key);
    }

    private boolean isPriceDifferent(Map.Entry<ProductName, ProductInfo> entry) {
        return shop.getProductSets().get(entry.getKey()).getPrice() != entry.getValue().getPrice();
    }

    private boolean isContainsValueTransaction(ProductName key) {
        return transaction.getProductSets().containsKey(key);
    }
}