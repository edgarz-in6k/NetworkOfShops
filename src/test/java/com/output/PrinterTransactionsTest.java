package com.output;

import com.core.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrinterTransactionsTest {

    private List<TransactionByCustomer> transactionList;
    private HashMap<ProductName, ProductInfo> hashMap;

    @Before
    public void setUp() throws Exception {
        transactionList = new ArrayList<>();
        hashMap = new HashMap<>();
        hashMap.put(ProductName.CHEESE, new ProductInfo(3, 4.5));
        hashMap.put(ProductName.POTATO, new ProductInfo(12, 34.84));
    }

    @Test
    public void testPrint() {
        transactionList.add(new TransactionByCustomer(
                new Customer("cust1"),
                new Shop("shop1"),
                hashMap));
        transactionList.add(new TransactionByCustomer(
                new Customer("cust1"),
                new Shop("shop1"),
                hashMap));
        PrinterTransactions printer = new PrinterTransactions(transactionList);
        assertEquals(printer.toString(),
                        "Customer name: cust1\n" +
                        "    Shop: shop1\n" +
                        "        POTATO: $34.84 x 12\n" +
                        "        CHEESE: $4.5 x 3\n" +
                        "Customer name: cust1\n" +
                        "    Shop: shop1\n" +
                        "        POTATO: $34.84 x 12\n" +
                        "        CHEESE: $4.5 x 3\n");
    }
}
