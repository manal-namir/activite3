package com.example.billing_service.services;

import com.example.billing_service.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//interface pour communiquer avec customerService
@FeignClient(name = "CUSTOMER-SERVICE", url = "http://localhost:8081")
//@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping(path = "/customers/{id}")
    Customer findCustomerById(@PathVariable Long id);
}
