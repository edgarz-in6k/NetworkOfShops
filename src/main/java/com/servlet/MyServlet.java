package com.servlet;

import com.core.*;
import com.db.CustomerDAO;
import com.output.AboutPurchasesCustomer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyServlet extends HttpServlet{

    private CustomerDAO customerQuery;
    private static final int SQL_QUERY_ALL_COUNT_COLUMN = 6;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        customerQuery = context.getBean("customerQuery", CustomerDAO.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<AboutPurchasesCustomer> aboutPurchasesCustomers = null;

        try {
            aboutPurchasesCustomers = AboutPurchasesCustomer.toArrayList(customerQuery.getCustomersInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("aboutPurchasesCustomers", aboutPurchasesCustomers);

        try {
            request.getRequestDispatcher("jsp/customerFavoriteShop.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        delete(request);

        add(request);

        showAddTransaction(request);

        customers(request);

        shops(request);

        doEdit(request, response);
    }

    private void delete(HttpServletRequest request) {
        String paramWidth = request.getParameter("del");
        if (paramWidth != null){
            int del = Integer.parseInt(paramWidth);
            try {
                customerQuery.deleteOperation(del);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void add(HttpServletRequest request) throws IOException {
        String paramNameCustomersInput = request.getParameter("nameCustomersInput");
        String paramNameShopsInput = request.getParameter("nameShopsInput");
        String paramProductsNameInput = request.getParameter("productsNameInput");
        String paramCount = request.getParameter("count");
        String paramPrice = request.getParameter("price");
        if (paramNameCustomersInput != null &&
                !paramNameCustomersInput.equals("") &&
                !paramNameShopsInput.equals("") &&
                !paramProductsNameInput.equals("") &&
                !paramCount.equals("") &&
                !paramPrice.equals("")){
            Map<ProductName, ProductInfo> productSets = new HashMap<>();
            productSets.put(
                    ProductName.createProductName(paramProductsNameInput),
                    new ProductInfo(Integer.parseInt(paramCount),
                            Double.parseDouble(paramPrice)));

            TransactionByCustomer transaction = new TransactionByCustomer(
                    new Customer(paramNameCustomersInput),
                    new Shop(paramNameShopsInput),
                    productSets);
            try {
                customerQuery.addTransaction(transaction);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getRequestDispatcher("jsp/edit.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void shops(HttpServletRequest request) {
        List list = null;
        try {
            list = customerQuery.getShops();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("nameShops", list);
    }

    private void customers(HttpServletRequest request) {
        List list = null;
        try {
            list = customerQuery.getCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("nameCustomers", list);
    }

    private void showAddTransaction(HttpServletRequest request) {
        List list = null;
        try {
            list = customerQuery.getAllTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("list", list);
        request.setAttribute("COUNT_COLUMN", SQL_QUERY_ALL_COUNT_COLUMN);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}