package com.sample01;

import com.sample01.entity.Category;
import com.sample01.entity.Product;
import com.sample01.repository.ProductRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void whenFindByCategory_thenReturnListProducts(){
        Product product = Product.builder()
                .name("Computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createAt(new Date())
                .build();
        productRepository.save(product);

        List<Product> founds = productRepository.findByCategory(product.getCategory());
        Assertions.assertThat(founds.size()).isEqualTo(3);
    }
}
