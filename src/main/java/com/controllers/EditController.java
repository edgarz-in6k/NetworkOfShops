package com.controllers;

import com.core.*;
import com.db.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/edit")
public class EditController {

    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("/show")
    public String edit(Model model){

        showAddTransaction(model);
        namesCustomers(model);
        namesShops(model);

        return "edit";
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

    private void namesCustomers(Model model) {
        List list = null;
        try {
            list = customerDAO.getCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("nameCustomers", list);
    }

    private void showAddTransaction(Model model) {
        List list = null;
        try {
            list = customerDAO.getAllTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("list", list);
    }

    @RequestMapping(value = "/deleteProduct/{prod_id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("prod_id") int prod_id){

        return "redirect:/edit/show";
    }

    @RequestMapping(value = "/deleteTransactionProduct/{main_id}", method = RequestMethod.POST)
    public String deleteTransactionProduct(@PathVariable("main_id") int main_id){

        return "redirect:/edit/show";
    }

    @RequestMapping(value = "/addTransaction/{main_id, a}", method = RequestMethod.POST)
    public String addTransaction(@PathVariable("main_id, a") int main_id, int a, Model model){
        /*String paramNameCustomersInput = request.getParameter("nameCustomersInput");
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
        }*/
        return "redirect:/edit/show";
    }
}
