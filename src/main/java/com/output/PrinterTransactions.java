package com.output;

import com.core.ProductName;
import com.core.ProductInfo;
import com.core.TransactionByCustomer;

import java.util.List;
import java.util.Map;

public class PrinterTransactions {

    public static final String NEW_LINE = "\n";
    private List<TransactionByCustomer> transactionList;

    public PrinterTransactions(List<TransactionByCustomer> transactionList) {
        this.transactionList = transactionList;
    }

    public void print(){
        System.out.print(this);
    }

    @Override
    public String toString(){
        String s = "";
        for (TransactionByCustomer transaction : transactionList){
            s += "Customer name: " + transaction.getCustomer().getName() + NEW_LINE;
            s += "    Shop: " + transaction.getShop().getName() + NEW_LINE;
            for (Map.Entry<ProductName, ProductInfo> entry: transaction.getProductSets().entrySet()){
                s += "        " + entry.getKey().name() + ": $" +
                        entry.getValue().getPrice() + " x " +
                        entry.getValue().getCount() + NEW_LINE;
            }
        }
        return s;
    }
}
