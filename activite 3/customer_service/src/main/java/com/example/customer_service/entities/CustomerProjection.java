package com.example.customer_service.entities;

import org.springframework.data.rest.core.config.Projection;

//classe pour la projection (on veut recuperer que id et name)
//http://localhost:8081/customers?projection=fullCustomer
@Projection(name="fullCustomer",types=Customer.class) //nomprojection fullcustomer, et va sappliquer sur lentite customer
public interface CustomerProjection {
    public Long getId();
    public String getName();
}
