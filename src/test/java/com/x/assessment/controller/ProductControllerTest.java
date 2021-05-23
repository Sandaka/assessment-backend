package com.x.assessment.controller;

import com.x.assessment.dto.ProductCard;
import com.x.assessment.entity.Product;
import com.x.assessment.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandaka Wijesinghe on 5/22/2021.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Before
    public void init() {

    }

    @Test
    public void getAllProductsTest() {
        List<ProductCard> productCardList = new ArrayList<>();

        ProductCard product1 = new ProductCard();
        ProductCard product2 = new ProductCard();

        productCardList.add(product1);
        productCardList.add(product2);

        Mockito.when(productService.getAllProducts()).thenReturn(productCardList);
        ResponseEntity<List<ProductCard>> response = productController.getAllProducts();
        List<ProductCard> productCardListActual = response.getBody();

        Assert.assertEquals(productCardList.size(), productCardListActual.size());
        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }

    @Test
    public void calculatePriceTest() {
        ProductCard product = new ProductCard();
        product.setProductId(1);
        product.setProductName("product1");
        product.setCartonPrice(175.00);
        product.setUnitsPerCarton(20);
        product.setQty(1);
        product.setOptionName("by_units");

        Mockito.when(productService.calculateProductPrice(product)).thenReturn(new ProductCard());
        ResponseEntity<ProductCard> response = productController.calculatePrice(product);
        ProductCard productCard = response.getBody();

        Assert.assertNotNull(productCard);
        Mockito.verify(productService, Mockito.times(1)).calculateProductPrice(product);
    }
}
