package com.example.customer_service.repositories;

import com.example.customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //pour mettre en place un webservice qui permet de gerer les customer
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
