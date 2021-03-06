package com.ikhz.controllers;

import com.ikhz.dto.EditProductValidation;
import com.ikhz.dto.ErrorResponse;
import com.ikhz.models.entities.Product;
import com.ikhz.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    // get all available product method
    @GetMapping
    public ResponseEntity<Object> getAllAvailable(){
        return productService.findAllAvailable();
    }
    // get all product method
    @GetMapping("/all")
    public ResponseEntity<Object> getAllProduct(@RequestParam(required = false) String search){
        return productService.findAll(search);
    }
    // create product method
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Product product, Errors errors, @RequestHeader HttpHeaders httpHeaders){

        String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        if(token == null){
            ErrorResponse errorResponse = new ErrorResponse("Cannot create product");
            errorResponse.getMessage().add("Authorization token is required");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
        String[] split = token.split(" ", 2);
        if(errors.hasErrors()){
            ErrorResponse errorResponse = new ErrorResponse("Cannot create product");

            for(ObjectError error: errors.getAllErrors()){
                errorResponse.getMessage().add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return productService.create(product, split[1]);
    }
    // buy product method
    @PostMapping("/buy")
    public ResponseEntity<Object> buyProduct(@Valid @RequestBody EditProductValidation buy, Errors errors, @RequestHeader HttpHeaders httpHeaders){
        ErrorResponse errorResponse = new ErrorResponse("Cannot buy product");
        if(errors.hasErrors()){

            for(ObjectError error: errors.getAllErrors()){
                errorResponse.getMessage().add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        if(token == null){
            errorResponse.getMessage().add("Authorization token is required");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
        String[] split = token.split(" ", 2);

        return productService.buyProduct(buy, split[1]);
    }
    //add product method
    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@Valid @RequestBody EditProductValidation addProduct, Errors errors, @RequestHeader HttpHeaders httpHeaders){
        ErrorResponse errorResponse = new ErrorResponse("Cannot buy product");
        if(errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
                errorResponse.getMessage().add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        if(token == null){
            errorResponse.getMessage().add("Authorization token is required");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
        String[] split = token.split(" ", 2);

        return productService.addProduct(addProduct, split[1]);
    }
}
