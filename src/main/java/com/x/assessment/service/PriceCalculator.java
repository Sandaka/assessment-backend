package com.x.assessment.service;

import com.x.assessment.dto.ProductCard;

/**
 * Created by Sandaka Wijesinghe on 5/21/2021.
 */
public interface PriceCalculator {

    /**
     * PriceCalculator method to Calculate Single Unit Price.
     *
     * @param product {@link ProductCard}.
     * @param units   {@type int}.
     * @return {@type double}.
     */
    public double calculateSingleUnitPrice(ProductCard product, int units);

    /**
     * PriceCalculator method to convert For Two DecimalPoints.
     *
     * @param value {@type double}.
     * @return {@type double}.
     */
    public double convertForTwoDecimalPoints(double value);

    /**
     * PriceCalculator method to Calculate Carton Price.
     *
     * @param product {@link ProductCard}.
     * @param cartons {@type int}.
     * @return {@type double}.
     */
    public double calculateCartonPrice(ProductCard product, int cartons);

    /**
     * PriceCalculator method to Calculate Total Price.
     *
     * @param product {@link ProductCard}.
     * @param cartons {@type int}.
     * @return {@type double}.
     */
    public double calculateTotalPrice(ProductCard product, int units, int cartons);

    /**
     * PriceCalculator method to Calculate Product Price.
     *
     * @param productCard {@link ProductCard}.
     * @return {@link ProductCard}.
     */
    public ProductCard calculateProductPrice(ProductCard productCard);
}
