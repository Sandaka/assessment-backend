package com.x.assessment.controller;

import com.x.assessment.dto.ProductCard;
import com.x.assessment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sandaka Wijesinghe on 5/20/2021.
 */

@RestController
@CrossOrigin
@RequestMapping("/99x/assessment")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * GET
     * Route to get all products.
     *
     * @return {@link ResponseEntity}.
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductCard>> getAllProducts() {
        List<ProductCard> productCardList = productService.getAllProducts();
        return ResponseEntity.ok().body(productCardList);
    }

    /**
     * POST
     * Route to calculate selected product amount.
     *
     * @param productCard {@link ProductCard} JSON.
     * @return {@link ResponseEntity}.
     */
    @PostMapping("/calculate_prices")
    public ResponseEntity<ProductCard> calculatePrice(@RequestBody ProductCard productCard) {
        ProductCard updatedProductCard = productService.calculateProductPrice(productCard);
        return ResponseEntity.ok().body(updatedProductCard);
    }
}
