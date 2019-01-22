package com.radek.bank.hibernate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "atms")
@Getter
@NoArgsConstructor
public class Atm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATM")
    private Integer id;

    @Column(name = "BALANCE")
    private double balance;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ATM", referencedColumnName = "ID_ATM")
    private List<CashOperation> cashOperationList;

    public Atm(double balance, String address, List<CashOperation> cashOperationList) {
        this.balance = balance;
        this.address = address;
        this.cashOperationList = cashOperationList;
    }
}
