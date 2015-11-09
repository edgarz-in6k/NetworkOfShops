package com.controllers;

import com.core.*;
import com.db.CustomerDAO;
import com.output.AboutProducts;
import com.output.AboutTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/edit")
public class EditController {

    @Autowired
    private CustomerDAO customerDAO;

    private static ArrayList<AboutProducts> products = new ArrayList<>();
    private String nameCustomer;
    private String nameShop;

    @RequestMapping("/show")
    public String edit(Model model){

        showAddTransaction(model);
        namesCustomers(model);
        namesShops(model);

        return "edit";
    }

    private void showAddTransaction(Model model) {
        List<AboutTransaction> aboutTransactions = null;
        try {
            aboutTransactions = AboutTransaction.toArrayList(customerDAO.getAllTransaction());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("aboutTransactions", aboutTransactions);
    }

    private void namesCustomers(Model model) {
        List list = null;
        try {
            list = customerDAO.getCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("nameCustomers", list);
    }

    private void namesShops(Model model) {
        List list = null;
        try {
            list = customerDAO.getShops();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("nameShops", list);
    }

    @RequestMapping(value = "/{prod_id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable int prod_id){
        try {
            customerDAO.deleteOperation(prod_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/edit/show";
    }

    @RequestMapping(value = "deleteTransactionProduct/", method = RequestMethod.DELETE)
    public String deleteTransactionProduct(@RequestParam("main_id") int main_id){
        try {
            customerDAO.deleteTransaction(main_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/edit/show";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTransaction(@RequestParam("nameCustomer") String nameCustomer,
                                 @RequestParam("nameShop") String nameShop,
                                 Model model){
        model.addAttribute("nameCustomer", nameCustomer);
        model.addAttribute("nameShop", nameShop);
        this.nameCustomer = nameCustomer;
        this.nameShop = nameShop;
        model.addAttribute("price", "price");
        model.addAttribute("products", products);
        return "addTransaction";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProductToTransaction(
                                 @RequestParam("nameProduct") String nameProduct,
                                 @RequestParam("count") String count,
                                 @RequestParam("price") String price,
                                 Model model){
        products.add(new AboutProducts(nameProduct, count, price));
        model.addAttribute("nameCustomer", nameCustomer);
        model.addAttribute("nameShop", nameShop);
        model.addAttribute("products", products);
        return "addTransaction";
    }

    @RequestMapping(value = "/endAddProduct", method = RequestMethod.POST)
    public String endAddProductToTransaction(){
        TransactionByCustomer transaction = new TransactionByCustomer(new Customer(nameCustomer), new Shop(nameShop));
        for (AboutProducts about : products){
            transaction.getProductSets().put(
                    ProductName.createProductName(about.getNameProduct()),
                    new ProductInfo(Integer.parseInt(about.getCount()), Double.parseDouble(about.getPrice())));
        }
        try {
            customerDAO.addTransaction(transaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        products = new ArrayList<>();
        nameCustomer = "";
        nameShop = "";

        return "redirect:/edit/show";
    }

    /*@RequestMapping(value = "/addTransaction/", method = RequestMethod.PUT)
    public String addTransaction(Model model, HttpServletRequest request){
        String paramNameCustomersInput = request.getParameter("nameCustomersInput");
        Map<ProductName, ProductInfo> productSets = new HashMap<>();
        productSets.put(ProductName.MILK, new ProductInfo(1, 2.0));
        TransactionByCustomer transaction = new TransactionByCustomer(
                new Customer(paramNameCustomersInput),
                new Shop("Shop_"),
                productSets);
        try {
            customerDAO.addTransaction(transaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        return "redirect:/edit/show";
    }*/
}

/*String paramNameCustomersInput = request.getParameter("nameCustomersInput");
        String paramNameShopsInput = request.getParameter("nameShopsInput");
        String paramProductsNameInput = request.getParameter("productsNameInput");
        String paramCount = request.getParameter("count");
        String paramPrice = request.getParameter("price");*/
        /*Map map = model.asMap();
        String paramNameCustomersInput = map.get("nameCustomersInput").toString();
        String paramNameShopsInput = map.get("nameShopsInput").toString();
        String paramProductsNameInput = map.get("productsNameInput").toString();
        String paramCount = map.get("count").toString();
        String paramPrice = map.get("price").toString();
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
                customerDAO.addTransaction(transaction);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            System.exit(1);*/