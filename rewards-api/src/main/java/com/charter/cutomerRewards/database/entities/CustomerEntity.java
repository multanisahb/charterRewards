package com.charter.cutomerRewards.database.entities;

import lombok.*;

import javax.persistence.*;

/**
 * This entity contains info for customer object
 * @author Multani
 */
@Getter
@Setter
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
}
