package com.sample01;

import com.sample01.entity.Category;
import com.sample01.entity.Product;
import com.sample01.repository.ProductRepository;
import com.sample01.services.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.sample01.services.ProductServices;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServicesMockTest {
    @Mock
    private ProductRepository productRepository;


    private ProductServices productServices;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productServices =  new ProductServiceImpl( productRepository);
        Product computer = Product.builder()
                .name("Computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createAt(new Date())
                .build();
        Mockito.when(productRepository.findById(1l))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer))
                .thenReturn(computer);

    }
    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productServices.getProduct(1l);
        Assertions.assertThat(found.getName()).isEqualTo("Computer");
    }
    @Test
    public void WhenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productServices.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(18);

    }
}
