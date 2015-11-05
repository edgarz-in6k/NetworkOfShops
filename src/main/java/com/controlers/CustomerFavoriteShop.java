package com.controlers;

import com.db.CustomerDAO;
import com.output.AboutPurchasesCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/customerFavoriteShop")
public class CustomerFavoriteShop {

//    @Autowired
//    private CustomerDAO customerQuery;

    @RequestMapping(method = RequestMethod.GET)
	public String customerFavoriteShop(ModelMap model) {

        List<AboutPurchasesCustomer> aboutPurchasesCustomers = null;

		model.addAttribute("aboutPurchasesCustomers", "<center><h1>Information of shops and shoppers</h1></center>");
		return "customerFavoriteShop";
    }

    /*try {
            //aboutPurchasesCustomers = AboutPurchasesCustomer.toArrayList(customerQuery.outSQLQuery(SQL_QUERY_CUSTOMER_INFO));
            aboutPurchasesCustomers = AboutPurchasesCustomer.toArrayList(customerQuery.getCustomersInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("aboutPurchasesCustomers", aboutPurchasesCustomers);
    }*/
}
