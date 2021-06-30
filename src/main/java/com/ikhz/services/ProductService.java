package com.ikhz.services;

import com.ikhz.dto.EditProductValidation;
import com.ikhz.dto.ErrorResponse;
import com.ikhz.models.entities.Product;
import com.ikhz.models.entities.Transaction;
import com.ikhz.models.entities.TransactionType;
import com.ikhz.models.entities.User;
import com.ikhz.models.repos.ProductRepo;
import com.ikhz.models.repos.TransactionRepo;
import com.ikhz.models.repos.UserRepo;
import com.ikhz.utility.EncryptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
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
    private TransactionRepo transactionRepo;

    @Autowired
    private EncryptionHelper encryptionHelper;

    // method to list all registered product
    public ResponseEntity<Object> findAll(){
        List<Product> products = (List<Product>) productRepo.findAll();

        if(products.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("No data Found");
            errorResponse.getMessage().add("No Data in Database");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(products);
    }
    // method to create product
    public ResponseEntity<Object> create(Product product, String token){
        String id = encryptionHelper.tokenDecryption(token);
        Optional<User> user = userRepo.findById(Long.parseLong(id));
        // add validation if user is not a suplier
        if(user.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("No data Found");
            errorResponse.getMessage().add("Suplier is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        // something else than model mapper
        Transaction transaction = new Transaction();
        transaction.setAmount(product.getProductStock());
        transaction.setTotal(product.getProductStock() * product.getProductPrice());
        transaction.setProduct(product);
        transaction.setUser(user.get());
        transaction.setTransactionType(TransactionType.ADD);

        product.setSuplier(user.get());
        transactionRepo.save(transaction);
        productRepo.save(product);

        return ResponseEntity.ok(product);
    }
    // method to buy product
    public ResponseEntity<Object> buyProduct(EditProductValidation buy, String token){
        ErrorResponse errorResponse = new ErrorResponse("Cannot buy product");
        String id = encryptionHelper.tokenDecryption(token);
        Optional<User> user = userRepo.findById(Long.parseLong(id));
        // add validation if user is not a suplier
        if(user.isEmpty()){
            errorResponse.getMessage().add("Suplier id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        Optional<Product> product = productRepo.findById(buy.getProductId());
        if(product.isEmpty()){
            errorResponse.getMessage().add("Product id not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        if(product.get().getProductStock() < buy.getAmount()){
            errorResponse.getMessage().add("Product stock is not available");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        product.get().setProductStock(product.get().getProductStock() - buy.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAmount(buy.getAmount());
        transaction.setTotal(product.get().getProductPrice() * buy.getAmount());
        transaction.setProduct(product.get());
        transaction.setUser(user.get());
        transaction.setTransactionType(TransactionType.BUY);

        transactionRepo.save(transaction);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Status", "Success");
        hashMap.put("Action", "Buy Product");
        hashMap.put("Product Name", product.get().getProductName());
        hashMap.put("Total", product.get().getProductPrice() * buy.getAmount());

        return ResponseEntity.ok(hashMap);
    }
    // method to add product
    public ResponseEntity<Object> addProduct(EditProductValidation add, String token){
        ErrorResponse errorResponse = new ErrorResponse("Cannot add product");
        String id = encryptionHelper.tokenDecryption(token);
        Optional<User> user = userRepo.findById(Long.parseLong(id));
        // add validation if user is not an admin
        if(user.isEmpty()){
            errorResponse.getMessage().add("Admin id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        Optional<Product> product = productRepo.findById(add.getProductId());
        if(product.isEmpty()){
            errorResponse.getMessage().add("Product id not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        product.get().setProductStock(product.get().getProductStock() + add.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAmount(add.getAmount());
        transaction.setTotal(product.get().getProductPrice() * add.getAmount());
        transaction.setProduct(product.get());
        transaction.setUser(user.get());
        transaction.setTransactionType(TransactionType.ADD);

        transactionRepo.save(transaction);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Status", "Success");
        hashMap.put("Action", "Add Product");
        hashMap.put("Product Name", product.get().getProductName());
        hashMap.put("Current Stock", product.get().getProductStock());

        return ResponseEntity.ok(hashMap);
    }
    // method to get all available product
    public ResponseEntity<Object> findAllAvailable(){
        List<Product> products = productRepo.findByProductStockGreaterThan(0);
        if(products.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("No data Found");
            errorResponse.getMessage().add("No product has available stock");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(products);
    }
    // delete method isn't necessary even product stock == 0
}
