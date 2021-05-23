package com.x.assessment.service;

import com.x.assessment.dto.ProductCard;

import java.util.List;

/**
 * Created by Sandaka Wijesinghe on 5/20/2021.
 */
public interface ProductService extends PriceCalculator{

    /**
     * Service method to get all products.
     *
     * @return {@link List}.
     */
    public List<ProductCard> getAllProducts();
}
