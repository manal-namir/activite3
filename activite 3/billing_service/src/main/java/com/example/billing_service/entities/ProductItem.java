package com.example.billing_service.entities;

import com.example.billing_service.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId; //un productitem correspond a un produit
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//pour ne pas retourner bill lors du retour du productitem
    private Bill bill; //un product se trouve dans une facture(bill) et chaque facture contient plusieurs productitem
    private int quantity;
    private double price;
    private double discount;
    @Transient //parce que Product n'a pas lannotation Entity
    private Product product;
}
