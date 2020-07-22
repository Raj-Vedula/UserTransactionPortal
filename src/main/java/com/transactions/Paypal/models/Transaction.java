package com.transactions.Paypal.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
//ORM DB mapping with dependencies in config files pom.xml and properties
@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private int transactionAmount;

    @Column(nullable = false)
    private LocalDate transactionDate;

    @Column(nullable = false)
    private LocalDateTime transactionTimeStamp;

    @Column(nullable = false)
    private String transactionUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDateTime getTransactionTimeStamp() {
        return transactionTimeStamp;
    }

    public void setTransactionTimeStamp(LocalDateTime transactionTimeStamp) {
        this.transactionTimeStamp = transactionTimeStamp;
    }

    public String getTransactionUser() {
        return transactionUser;
    }

    public void setTransactionUser(String transactionUser) {
        this.transactionUser = transactionUser;
    }
}
