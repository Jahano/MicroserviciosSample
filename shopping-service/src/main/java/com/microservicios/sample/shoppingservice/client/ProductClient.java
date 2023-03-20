package com.microservicios.sample.shoppingservice.client;


import com.microservicios.sample.shoppingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service")
@RequestMapping(path = "/products")
public interface ProductClient {

    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);
    @GetMapping(path = "/{id}/{stock}")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id,@PathVariable("stock") Double stock);
}
