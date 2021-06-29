package com.ikhz.models.repos;

import com.ikhz.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findByProductStockGreaterThan(long productStock) ;
}
