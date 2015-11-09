package com.output;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AboutProducts implements Iterable<String> {

    private ArrayList<String> info = new ArrayList<>();

    public AboutProducts(){
        info = new ArrayList<>();
    }

    public AboutProducts(String nameProduct, String count, String price) {
        info.add(nameProduct);
        info.add(count);
        info.add(price);
    }

    public static List<AboutProducts> toArrayList(List list) {
        List<AboutProducts> AboutProducts = new ArrayList<>();
        for (Object object : list){
            Object[] objects = (Object[])object;
            AboutProducts.add(new AboutProducts(
                    objects[0].toString(),
                    objects[1].toString(),
                    objects[2].toString()));
        }
        return AboutProducts;
    }

    public String getNameProduct() {
        return info.get(0);
    }

    public String getCount() {
        return info.get(1);
    }

    public String getPrice() {
        return info.get(2);
    }

    @Override
    public Iterator<String> iterator() {
        return info.iterator();
    }
}
