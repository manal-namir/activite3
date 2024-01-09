package com.example.billing_service.model;

import lombok.Data;
//on va pas mettre entities parce qu'on ne veut pas creer un table dans la bd
@Data //getters,setter
public class Customer {
    private Long id;
    private String name;
    private String email;
}
