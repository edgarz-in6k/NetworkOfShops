package com.controllers;

import com.db.CustomerDAO;
import com.output.AboutPurchasesCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class CustomerFavoriteShop {

    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("/customerFavoriteShop")
	public String customerFavoriteShop(ModelMap model) {


        List<AboutPurchasesCustomer> aboutPurchasesCustomers = null;
        try {
            aboutPurchasesCustomers = AboutPurchasesCustomer.toArrayList(customerDAO.getCustomersInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("aboutPurchasesCustomers", aboutPurchasesCustomers);
		return "showCustomerFavoriteShop.jsp";
    }
}