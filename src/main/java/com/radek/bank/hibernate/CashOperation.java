package com.radek.bank.hibernate;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_operations")
@Getter
@NoArgsConstructor
public class CashOperation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CASH_OPERATION")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ATM", referencedColumnName = "ID_ATM")
    private Atm atm;

    @Column(name = "DATETIME")
    private LocalDateTime dateTime;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OPERATION_METHOD")
    private String operationMethod;

    @Column(name = "OPERATION_TYPE")
    private String operationType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER")
    private Account account;

    public CashOperation(Atm atm, LocalDateTime dateTime, double amount, String description, String operationMethod, String operationType, Account account) {
        this.atm = atm;
        this.dateTime = dateTime;
        this.amount = amount;
        this.description = description;
        this.operationMethod = operationMethod;
        this.operationType = operationType;
        this.account = account;
    }
}
