package com.example.billing_service;

import com.example.billing_service.entities.Bill;
import com.example.billing_service.entities.ProductItem;
import com.example.billing_service.model.Customer;
import com.example.billing_service.model.Product;
import com.example.billing_service.repositories.BillRepository;
import com.example.billing_service.repositories.ProductItemRepository;
import com.example.billing_service.services.CustomerRestClient;
import com.example.billing_service.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients//pour reconnaitre les interface openFeign(customerrestclient et productrestclient)
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return args -> {
            //on veut creer des facture(bill) avec des produits qui existent deja il faut les recuperer dabord
            Collection<Product> products=productRestClient.allProducts().getContent();//getcontent retourne une collection
            Long customerId=1L;
            Customer customer=customerRestClient.findCustomerById(customerId);
            if(customer==null) throw new RuntimeException("Customer not found");
            Bill bill=new Bill();
            bill.setBillDate(new Date());
            bill.setCustomerId(customerId);
            Bill savedBill=billRepository.save(bill);
            products.forEach(product -> {
                ProductItem productItem=new ProductItem();
                productItem.setBill(savedBill);
                productItem.setProductId(product.getId());
                productItem.setQuantity(1+new Random().nextInt(10));//nombre aleatoire entre 0 et 10 et pour ne pas avoir 0 c'est le +1
                productItem.setPrice(product.getPrice());
                productItem.setDiscount(Math.random());//nombre entre 0 et 1
                productItemRepository.save(productItem);

            });

        };
    }
}
