package com.example;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account_info")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String firstName;

    private String lastName;
}
