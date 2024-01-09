package com.example.billing_service.entities;

import com.example.billing_service.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billDate;
    private Long customerId;//une facture correspond a un client
    @OneToMany(mappedBy = "bill")//lattribut bill dans productitem
    private List<ProductItem> productItems;
    @Transient//une facon de dire a jpa que cette attribut existe dans la classe mais nexiste pas dans la bd ces données vont etre recuperer d'un autre emplacement
    private Customer customer;


}
