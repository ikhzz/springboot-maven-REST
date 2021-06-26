package com.ikhz.models.repos;

import com.ikhz.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
