package com.example;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
