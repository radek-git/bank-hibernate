package com.radek.bank.hibernate;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    private User user;

    @Column(name = "BALANCE")
    private double balance;

    public Account(String accountNr, User user, double balance) {
        this.accountNr = accountNr;
        this.user = user;
        this.balance = balance;
    }
}
