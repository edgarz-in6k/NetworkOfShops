package com.output;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AboutTransaction implements Iterable<String> {
    private ArrayList<String> info = new ArrayList<>();

    public AboutTransaction(String main_id,
                            String nameCustomer,
                            String nameShop,
                            String prod_id,
                            String nameProduct,
                            String count,
                            String price) {
        info.add(main_id);
        info.add(nameCustomer);
        info.add(nameShop);
        info.add(prod_id);
        info.add(nameProduct);
        info.add(count);
        info.add(price);
    }

    public static List<AboutTransaction> toArrayList(List list) {
        List<AboutTransaction> aboutTransaction = new ArrayList<>();
        for (Object object : list){
            Object[] objects = (Object[])object;
            aboutTransaction.add(new AboutTransaction(
                    objects[0].toString(),
                    objects[1].toString(),
                    objects[2].toString(),
                    objects[3].toString(),
                    objects[4].toString(),
                    objects[5].toString(),
                    objects[6].toString()));
        }
        return aboutTransaction;
    }

    public String getId() {
        return info.get(0);
    }

    public String getNameCustomer() {
        return info.get(1);
    }

    public String getNameShop() {
        return info.get(2);
    }

    public String getProdId() {
        return info.get(3);
    }

    public String getNameProduct() {
        return info.get(4);
    }

    public String getCount() {
        return info.get(5);
    }

    public String getPrice() {
        return info.get(6);
    }

    @Override
    public Iterator<String> iterator() {
        return info.iterator();
    }
}
