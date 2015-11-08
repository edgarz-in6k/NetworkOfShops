package com.output;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AboutPurchasesCustomer implements Iterable<String> {

    private ArrayList<String> info = new ArrayList<>();

    public AboutPurchasesCustomer(String nameCustomer, String nameShop, String visit, String sum) {
        info.add(nameCustomer);
        info.add(nameShop);
        info.add(visit);
        info.add(roundSum(sum));
    }

    public static List<AboutPurchasesCustomer> toArrayList(List list) {
        List<AboutPurchasesCustomer> aboutPurchasesCustomers = new ArrayList<>();
        for (Object object : list){
            Object[] objects = (Object[])object;
            aboutPurchasesCustomers.add(new AboutPurchasesCustomer(
                    objects[0].toString(),
                    objects[1].toString(),
                    objects[2].toString(),
                    objects[3].toString()));
        }
        return aboutPurchasesCustomers;
    }

    private String roundSum(String sum) {
        double d = Double.parseDouble(sum);
        return String.valueOf(roundedToTwoHundredths(d));
    }

    private double roundedToTwoHundredths(double d) {
        return Math.round(d*100)/100.0;
    }

    public String getNameCustomer() {
        return info.get(0);
    }

    public String getNameShop() {
        return info.get(1);
    }

    public String getVisit() {
        return info.get(2);
    }

    public String getSum() {
        return info.get(3);
    }

    @Override
    public Iterator<String> iterator() {
        return info.iterator();
    }
}