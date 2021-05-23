package com.x.assessment.service;

import com.x.assessment.dto.ProductCard;
import com.x.assessment.entity.Product;
import com.x.assessment.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandaka Wijesinghe on 5/21/2021.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private double priceIncreasePercentage;

    private int minimumCartons;

    private double cartonDiscount;

    private String discountMessage;

    @Before
    public void init() {
        priceIncreasePercentage = 0.3;
        minimumCartons = 3;
        cartonDiscount = 0.1;
        discountMessage = "message";
    }

    @Test
    public void getAllProductsWhenListIsNotEmpty() {
        List<ProductCard> productCardList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setId((long) 1);
        product1.setProductName("product1");
        product1.setCartonPrice(175.00);
        product1.setUnitsPerCarton(20);

        Product product2 = new Product();
        product2.setId((long) 2);
        product2.setProductName("product2");
        product2.setCartonPrice(825.00);
        product2.setUnitsPerCarton(5);

        productList.add(product1);
        productList.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(productList);

        //test
        productCardList = productService.getAllProducts();

        Assert.assertEquals(productList.size(), productCardList.size());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getAllProductsWhenListIsEmpty() {
        List<Product> productList =  new ArrayList<>();;
        List<ProductCard> productCardList =  new ArrayList<>();;

        Mockito.when(productRepository.findAll()).thenReturn(productList);

        productCardList = productService.getAllProducts();

        Assert.assertEquals(productList.size(), productCardList.size());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void calculateSingleUnitPriceTestWhenProductIsNotNull() {
        ProductCard product = new ProductCard();
        product.setProductId(1);
        product.setProductName("product1");
        product.setCartonPrice(175.00);
        product.setUnitsPerCarton(20);

        int units = 1;
        double unitPriceExpected = 61.25;
        double unitPriceActual = productService.calculateSingleUnitPrice(product, units);

        Assert.assertEquals(unitPriceExpected, unitPriceActual, 0);

    }

    @Test
    public void calculateSingleUnitPriceTestWhenProductIsNull() {
//        Mockito.doReturn("0.00").when(productService.calculateSingleUnitPrice(null,1));
        double unitPriceActual = productService.calculateSingleUnitPrice(null, 1);
        double unitPriceExpected = 0.00;
        Assert.assertEquals(unitPriceExpected, unitPriceActual, 0);
    }

    @Test
    public void calculateCartonPriceWhenExceedMinCartons() {
        ProductCard product = new ProductCard();
        product.setProductId(1);
        product.setProductName("product1");
        product.setCartonPrice(175.00);
        product.setUnitsPerCarton(20);

        int cartons = 4;

        double expectedPrice = 682.50;
        double actualPrice = productService.calculateCartonPrice(product, cartons);
        Assert.assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void calculateCartonPriceWhenLessThanMinCartons() {
        ProductCard product = new ProductCard();
        product.setProductId(1);
        product.setProductName("product1");
        product.setCartonPrice(175.00);
        product.setUnitsPerCarton(20);

        double expectedPrice = 175.00;
        double actualPrice = productService.calculateCartonPrice(product, 1);

        Assert.assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void calculateTotalPriceWhenUnitsGreaterThanZeroCartonsEqualsZero_firstCondition(){
        ProductCard product = new ProductCard();
        product.setProductId(1);
        product.setProductName("product1");
        product.setCartonPrice(175.00);
        product.setUnitsPerCarton(20);
        product.setQty(25);

        double expectedPrice = 481.25;
        double actualPrice = productService.calculateTotalPrice(product, product.getQty(),0);

        Assert.assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void calculateTotalPriceWhenUnitsGreaterThanZeroCartonsEqualsZero_secondCondition(){
        ProductCard product = new ProductCard();
        product.setProductId(1);
        product.setProductName("product1");
        product.setCartonPrice(175.00);
        product.setUnitsPerCarton(20);
        product.setQty(1);

        double expectedPrice = 61.25;
        double actualPrice = productService.calculateTotalPrice(product, product.getQty(),0);

        Assert.assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void calculateProductPriceWhenProductCardIsNotNullGetByUnits(){
        ProductCard product = new ProductCard();
        product.setProductId(1);
        product.setProductName("product1");
        product.setCartonPrice(175.00);
        product.setUnitsPerCarton(20);
        product.setQty(1);
        product.setOptionName("by_units");
        product.setTotalProductAmount(61.25);

        double expectedTotal = 61.25;
        ProductCard productCard = productService.calculateProductPrice(product);

        Assert.assertEquals(expectedTotal, productCard.getTotalProductAmount(),0);
    }

    @Test
    public void calculateProductPriceWhenProductCardIsNotNullGetByCartons(){
        ProductCard product = new ProductCard();
        product.setProductId(1);
        product.setProductName("product1");
        product.setCartonPrice(175.00);
        product.setUnitsPerCarton(20);
        product.setQty(1);
        product.setOptionName("by_cartons");
        //product.setTotalProductAmount(61.25);

        double expectedTotal = 175.00;
        ProductCard productCard = productService.calculateProductPrice(product);

        Assert.assertEquals(expectedTotal, productCard.getTotalProductAmount(),0);
    }

    @Test
    public void calculateProductPriceWhenProductCardIsNull(){
        ProductCard productCard = productService.calculateProductPrice(null);
        Assert.assertNull(productCard);
    }
}
