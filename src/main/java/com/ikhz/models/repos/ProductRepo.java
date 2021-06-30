package com.ikhz.models.repos;

import com.ikhz.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Product Interface class extend basic crud class from spring boot with basic method
public interface ProductRepo extends CrudRepository<Product, Long> {
    // derived spring boot query method to get product greater than parameter
    List<Product> findByProductStockGreaterThan(long productStock) ;
}
