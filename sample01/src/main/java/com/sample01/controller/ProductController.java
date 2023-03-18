package com.sample01.controller;

import com.sample01.entity.Category;
import com.sample01.entity.Product;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sample01.services.ProductServices;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(
            @RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> listProducts = new ArrayList<>();

        if(categoryId == null){
             listProducts =productServices.listAllProduct();
            if(listProducts.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            listProducts = productServices.findByCategory(Category.builder().id(categoryId).build());

            if(listProducts.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(listProducts);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productServices.getProduct(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product productCreate = productServices.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
        product.setId(id);
        Product productDb = productServices.updateProduct(product);
        if(productDb == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDb);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Product productDelete = productServices.deleteProduct(id);
        if(productDelete == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(productDelete);
    }
    @GetMapping(path = "/{id}/{stock}")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id,@PathVariable("stock") Double stock){
        Product product = productServices.updateStock(id, stock);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    private String formatMessage(BindingResult result){

        List<Map<String,String>> errors = result.getFieldErrors()
                .stream().map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
        JSONObject errorJson = new JSONObject(errorMessage);
        return errorJson.toString();
    }


}
