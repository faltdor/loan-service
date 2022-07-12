package com.faltdor.card.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int customerId;
    private String name;
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "create_dt")
    private LocalDate createDt;

}