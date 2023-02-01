package com.charter.cutomerRewards.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
}
