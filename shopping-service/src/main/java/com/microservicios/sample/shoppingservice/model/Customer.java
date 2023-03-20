package com.microservicios.sample.shoppingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
@Data
public class Customer {


    private Long id;


    private String numberId;


    private String firstName;

    private String lastName;

    private String email;


    private String  photoUrl;

    private Region region;

    private String state;

}
