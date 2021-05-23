package com.x.assessment.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by Sandaka Wijesinghe on 5/22/2021.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductCardTest {

    @InjectMocks
    private ProductCard productCard;

    private long productId;
    private String productName;
    private double unitPrice;
    private double cartonPrice;
    private String discountMsg;
    private int singleUnitQty;
    private int cartonQty;
    private int unitsPerCarton;
    private double totalProductAmount;

    @Before
    public void init() {
        productId = 1;
        productName = "Horseshoe";
        unitPrice = 100.00;
        cartonPrice = 875.00;
        discountMsg = "message";
        singleUnitQty = 3;
        cartonQty = 1;
        unitsPerCarton = 5;
        totalProductAmount = 2000.00;
    }

    @Test
    public void testGettersSetters() {
        productCard.setProductId(1);
        Assert.assertEquals(productId, productCard.getProductId());

        productCard.setProductName("Horseshoe");
        Assert.assertEquals(productName, productCard.getProductName());

        productCard.setUnitPrice(100.00);
        Assert.assertEquals(unitPrice, productCard.getUnitPrice(), 0);

        productCard.setCartonPrice(875.00);
        Assert.assertEquals(cartonPrice, productCard.getCartonPrice(), 0);

        productCard.setDiscountMsg("message");
        Assert.assertEquals(discountMsg, productCard.getDiscountMsg());

        productCard.setQty(3);
        Assert.assertEquals(singleUnitQty, productCard.getQty());

        productCard.setUnitsPerCarton(5);
        Assert.assertEquals(unitsPerCarton, productCard.getUnitsPerCarton());

        productCard.setTotalProductAmount(2000.00);
        Assert.assertEquals(totalProductAmount, productCard.getTotalProductAmount(), 0);
    }
}
