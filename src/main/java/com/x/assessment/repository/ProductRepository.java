package com.x.assessment.repository;

import com.x.assessment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sandaka Wijesinghe on 5/20/2021.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
