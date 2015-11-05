package com.output;

import com.db.CustomerDAO;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrinterQueryConsoleInfoCustomer extends PrinterQuery<PrintStream> {

    private List<AboutPurchasesCustomer> aboutPurchasesCustomers = new ArrayList<>();
    public static final String DASH = "-";
    public static final String CROSS = "+";
    public static final String PIPE = "|";
    private static final int COUNT_COLUMN = 4;
    private static final String[] header = {"Customers", "Shops", "Count visits", "Sum $"};
    private static final String SQL_QUERY_CUSTOMER_INFO =
            "SELECT TT.nameCustomer, TT.nameShop, TT.ct, sum(PP.count*PP.price) " +
                    "FROM " +
                    "(SELECT * " +
                    "FROM " +
                    "(SELECT nameCustomer, nameShop, count(nameShop) ct " +
                    "FROM Main GROUP BY nameCustomer, nameShop " +
                    "ORDER BY ct DESC) T " +
                    "GROUP BY nameCustomer) TT, " +
                    "(SELECT M.nameCustomer, M.nameShop, P.count, P.price " +
                    "FROM Main M, Product P " +
                    "WHERE M.main_id = P.fk_main_id ORDER BY M.nameCustomer) PP " +
                    "WHERE TT.nameCustomer = PP.nameCustomer and TT.nameShop = PP.nameShop " +
                    "GROUP BY TT.nameCustomer;";

    @Override
    public void print(CustomerDAO customerQuery) throws SQLException {
        initArrayList(customerQuery);
        outConsole();
    }

    private void initArrayList(CustomerDAO customerQuery) throws SQLException {
        aboutPurchasesCustomers = AboutPurchasesCustomer.toArrayList(customerQuery.outSQLQuery(SQL_QUERY_CUSTOMER_INFO));
    }

    private void outConsole() {
        int maxWidth = getMaxWidth();
        String formatOutput = "%" + maxWidth + "s" + PIPE;

        outTrait(maxWidth);

        printOut.print(PIPE);
        for (int i = 0; i < COUNT_COLUMN; i++)
            printOut.format(formatOutput, header[i]);
        printOut.println();

        outTrait(maxWidth);

        for (AboutPurchasesCustomer aboutPurchasesCustomer : aboutPurchasesCustomers) {
            printOut.print(PIPE);
            for (String component : aboutPurchasesCustomer)
                printOut.format(formatOutput, component);
            printOut.println();
            outTrait(maxWidth);
        }
        printOut.println();
    }

    private int getMaxWidth() {
        int maxWidth = 0;
        for (String s : header)
            maxWidth = Math.max(maxWidth, s.length());

        for (AboutPurchasesCustomer purchases : aboutPurchasesCustomers){
            for (String str : purchases)
                maxWidth = Math.max(maxWidth, str.length());
        }
        return maxWidth;
    }

    private void outTrait(int maxWidth) {
        printOut.print(CROSS);
        for (int i = 0; i < COUNT_COLUMN; i++) {
            for (int j = 0; j < maxWidth; j++)
                printOut.print(DASH);
            printOut.print(CROSS);
        }
        printOut.println();
    }
}