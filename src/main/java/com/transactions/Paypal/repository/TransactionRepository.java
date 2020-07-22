
package com.transactions.Paypal.repository;

import com.transactions.Paypal.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT u FROM Transaction u WHERE u.transactionDate = ?1")
    List<Transaction> findTransactionBytransactionDate(LocalDate transactionDate);

    @Query("SELECT u FROM Transaction u WHERE u.transactionType = ?1 and u.transactionUser = ?2")
    List<Transaction> findTransactionByTypeAndUser(String transactionType,String transactionUser);

    @Query("SELECT u FROM Transaction u WHERE year(u.transactionDate) = ?1 and u.transactionUser = ?2")
    List<Transaction> findTransactionForUserByYear(int year,String transactionUser);

    // Month based transactions
    @Query("SELECT u FROM Transaction u WHERE MONTH(u.transactionDate) = ?1 and u.transactionUser = ?2")
    List<Transaction> findTransactionForUserByMonth(int month,String transactionUser);

    // Day based transactions
    @Query("SELECT u FROM Transaction u WHERE DAYOFWEEK(u.transactionDate) = ?1 and u.transactionUser = ?2")
    List<Transaction> findTransactionForUserByDay(int day,String transactionUser);

    // Hour based transactions
    @Query("SELECT u FROM Transaction u WHERE HOUR(u.transactionDate) = ?1 and u.transactionUser = ?2")
    List<Transaction> findTransactionForUserByHour(int hour,String transactionUser);
}

