package com.ikhz.services;

import com.ikhz.dto.ErrorResponse;
import com.ikhz.models.entities.Product;
import com.ikhz.models.entities.User;
import com.ikhz.models.repos.ProductRepo;
import com.ikhz.models.repos.UserRepo;
import com.ikhz.utility.EncryptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EncryptionHelper encryptionHelper;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        List<Product> products = (List<Product>) productRepo.findAll();

        if(products.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("No data Found");
            errorResponse.getMessage().add("No Data in Database");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(products);
    }
    @PostMapping
    public ResponseEntity create(Product product, String token){
        String id = encryptionHelper.tokenDecryption(token);
        Optional<User> user = userRepo.findById(Long.parseLong(id));
        if(user.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("No data Found");
            errorResponse.getMessage().add("Suplier is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        product.setSuplier(user.get());
        productRepo.save(product);
        return ResponseEntity.ok("ok");
    }


}
