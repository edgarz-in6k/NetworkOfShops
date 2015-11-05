package com.core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductAboutPurchasesCustomerTest {
    @Test
    public void testEquals(){
        ProductInfo settingsOne = new ProductInfo(20, 4);
        ProductInfo settingsTwo = new ProductInfo(20, 4);
        assertTrue(settingsOne.equals(settingsTwo));
        ProductInfo settingsThree = new ProductInfo(10, 2);
        ProductInfo settingsFour = new ProductInfo(10, 4);
        assertFalse(settingsThree.equals(settingsFour));
        ProductInfo settingsFive = new ProductInfo(10, 4);
        ProductInfo settingsSex = new ProductInfo(11, 4);
        assertFalse(settingsFive.equals(settingsSex));
    }
}
