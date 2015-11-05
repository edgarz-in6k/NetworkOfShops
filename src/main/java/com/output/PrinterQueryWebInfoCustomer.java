package com.output;

import com.db.CustomerDAO;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrinterQueryWebInfoCustomer extends PrinterQuery<PrintWriter> {

    private List<AboutPurchasesCustomer> aboutPurchasesCustomers = new ArrayList<>();
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

    }
}
