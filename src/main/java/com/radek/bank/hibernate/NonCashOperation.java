package com.radek.bank.hibernate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "non_cash_operations")
@Getter
@NoArgsConstructor
public class NonCashOperation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OPERATION")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_ACCOUNT_NUMBER")
    private Account accountFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_ACCOUNT_NUMBER")
    private Account accountTo;

    @Column(name = "DATETIME")
    private LocalDateTime dateTime;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OPERATION_TYPE")
    private String type;


    public NonCashOperation(Account accountFrom, Account accountTo, LocalDateTime dateTime, double amount, String description, String type) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.dateTime = dateTime;
        this.amount = amount;
        this.description = description;
        this.type = type;
    }
}
