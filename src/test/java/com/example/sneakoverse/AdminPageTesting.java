package com.example.sneakoverse;

import com.example.sneakoverse.entity.AdminPage;
import com.example.sneakoverse.repo.AdminPageRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminPageTesting {
    @Autowired
    private AdminPageRepo adminPageRepo;

    @Test

    @Order(1)

    @Rollback(value = false)

    public void saveProductTest(){

        AdminPage product = AdminPage.builder()

                .item_name("Nike Af-1")

                .item_price("5000")

                .item_rating(4)

                .build();


        adminPageRepo.save(product);



        Assertions.assertThat(product.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public  void getProductTest(){
        AdminPage prodsent= adminPageRepo.findById(1).get();
        Assertions.assertThat(prodsent.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfProductTest(){
        List<AdminPage> prod = adminPageRepo.findAll();
        Assertions.assertThat(prod.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateProductPriceTest(){

        AdminPage prod = adminPageRepo.findById(1).get();

        prod.setItem_price("Rs 6500");

        AdminPage productUpdated =  adminPageRepo.save(prod);

        Assertions.assertThat(productUpdated.getItem_price()).isEqualTo("Rs 6500");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteProductTest(){
        AdminPage prod = adminPageRepo.findById(1).get();
        adminPageRepo.delete(prod);

        AdminPage prod1 = null;
        Optional<AdminPage> isMsg = adminPageRepo.findById(1);
        if(isMsg.isPresent()){
            prod1 = isMsg.get();
        }
        Assertions.assertThat(prod1).isNull();
    }
}
