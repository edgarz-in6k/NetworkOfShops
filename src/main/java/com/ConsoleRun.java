package com;

import com.core.TransactionByCustomer;
import com.db.CustomerDAO;
import com.output.PrinterQueryConsoleInfoCustomer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class ConsoleRun {
    public static void main(String[] args) throws SQLException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:/com/applicationContext.xml"}, true);

        CustomerDAO customerQuery = context.getBean("customerQuery", CustomerDAO.class);

        //customerQuery.deleteOperation(4);
        customerQuery.deleteAll();
        addToTable(context, customerQuery);

        PrinterQueryConsoleInfoCustomer printerQueryConsoleInfoCustomer = new PrinterQueryConsoleInfoCustomer();
        printerQueryConsoleInfoCustomer.printToStream(System.out);
        printerQueryConsoleInfoCustomer.print(customerQuery);

    }

    private static void addToTable(ClassPathXmlApplicationContext context, CustomerDAO customerQuery) throws SQLException {
        TransactionByCustomer transaction1 = context.getBean("transaction1", TransactionByCustomer.class);
        TransactionByCustomer transaction2 = context.getBean("transaction2", TransactionByCustomer.class);
        TransactionByCustomer transaction3 = context.getBean("transaction3", TransactionByCustomer.class);

        customerQuery.addTransaction(transaction1);
        customerQuery.addTransaction(transaction2);
        customerQuery.addTransaction(transaction3);

        customerQuery.addTransaction(transaction3);
        customerQuery.addTransaction(transaction3);
    }
}