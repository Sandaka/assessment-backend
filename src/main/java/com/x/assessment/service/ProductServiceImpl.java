package com.x.assessment.service;

import com.x.assessment.dto.ProductCard;
import com.x.assessment.entity.Product;
import com.x.assessment.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandaka Wijesinghe on 5/20/2021.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Value("${assessment.price-increase-percentage}")
    private double priceIncreasePercentage = 0.3;

    @Value("${assessment.discount.minimum-cartons}")
    private int minimumCartons = 3;

    @Value("${assessment.carton.discount}")
    private double cartonDiscount = 0.1;

    @Value("${assessment.carton.discount-msg}")
    private String discountMessage;

    @Value("${assessment.product.option1}")
    private String option1 = "by_units"; // by units

    @Value("${assessment.product.option2}")
    private String option2 = "by_cartons"; // by cartons

    @Override
    public List<ProductCard> getAllProducts() {
        logger.info("executing getAllProducts");
        List<Product> productList = productRepository.findAll();
        List<ProductCard> productCardList = new ArrayList<>();
        ;
        if (0 < productList.size()) {
            for (Product product : productList) {
                ProductCard productCard = new ProductCard();
                productCard.setCartonPrice(product.getCartonPrice());
                productCard.setDiscountMsg(discountMessage);
                productCard.setProductId(product.getId());
                productCard.setProductName(product.getProductName());
                productCard.setCartonPrice(product.getCartonPrice());
                productCard.setUnitsPerCarton(product.getUnitsPerCarton());
                productCard.setUnitPrice(((product.getCartonPrice() / product.getUnitsPerCarton()) + (product.getCartonPrice() * priceIncreasePercentage)) * 1);

                productCardList.add(productCard);
            }
        }
        return productCardList;
    }

    @Override
    public double calculateSingleUnitPrice(ProductCard product, int units) {
        double unitPrice = 0.00;
        if (null != product) {
            unitPrice = (((product.getCartonPrice() / product.getUnitsPerCarton()) + (product.getCartonPrice() * priceIncreasePercentage)) * units);
            unitPrice = convertForTwoDecimalPoints(unitPrice);
        }

        return unitPrice;
    }

    @Override
    public double calculateCartonPrice(ProductCard product, int cartons) {
        double cartonsPrice = 0.00;
        if (cartons >= minimumCartons) {
            cartonsPrice = ((product.getCartonPrice() * cartons) - (product.getCartonPrice() * cartonDiscount));
        } else {
            cartonsPrice = product.getCartonPrice() * cartons;
        }

        return cartonsPrice;
    }

    @Override
    public double calculateTotalPrice(ProductCard product, int units, int cartons) {
        double totalPrice = 0.00;
        if (units > 0 && cartons == 0) {

            // get product from db and compare
            if (units > product.getUnitsPerCarton()) {
                // get mod and calculate prices
                int asCartons = units / product.getUnitsPerCarton();
                int remainUnits = units % product.getUnitsPerCarton();

                totalPrice = calculateCartonPrice(product, asCartons) + calculateSingleUnitPrice(product, remainUnits);
            } else {
                // calculate single units prices
                totalPrice = calculateSingleUnitPrice(product, units);
            }
        } else if (units > 0 && cartons > 0) {
            if (units > product.getUnitsPerCarton()) {
                // get mod and calculate prices
                int asCartons = units / product.getUnitsPerCarton();
                int remainUnits = units % product.getUnitsPerCarton();

                totalPrice = calculateCartonPrice(product, asCartons) + calculateSingleUnitPrice(product, remainUnits) + calculateCartonPrice(product, cartons);
            } else {
                // calculate single units prices
                totalPrice = calculateSingleUnitPrice(product, units) + calculateCartonPrice(product, cartons);
            }
        } else if (units == 0 && cartons > 0) {
            totalPrice = calculateCartonPrice(product, cartons);
        } else {
            return totalPrice;
        }

        return totalPrice;
    }

    @Override
    public ProductCard calculateProductPrice(ProductCard productCard) {
        if (null != productCard) {
            if (productCard.getOptionName().toLowerCase().equals(option1)) {
                productCard.setTotalProductAmount(calculateTotalPrice(productCard, productCard.getQty(), 0));
            } else if (productCard.getOptionName().toLowerCase().equals(option2)) {
                productCard.setTotalProductAmount(calculateTotalPrice(productCard, 0, productCard.getQty()));
            } else {
                return productCard;
            }
        }
        return productCard;
    }

    @Override
    public double convertForTwoDecimalPoints(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String newValue = decimalFormat.format(value);
        return Double.parseDouble(newValue);
    }

}
