package com.core;

import org.junit.Before;

public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Customer");
    }
}