package com.appbackend.mathjungle.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;

    // One-to-one relationship with the parent's Users entity
    @OneToOne
    @JoinColumn(referencedColumnName = "userID")
    private Users parent;
}
