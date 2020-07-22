//Test cases for TransactionController.java goes here
package com.transactions.Paypal.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.transactions.Paypal.models.Transaction;
import com.transactions.Paypal.repository.TransactionRepository;
import com.transactions.Paypal.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;


@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public TransactionRepository transactionRepository;

    @MockBean
    public AuthenticationService authenticationService;

    @Test
    public void getDailyTransactionsTest() throws Exception {
        when(transactionRepository.findTransactionBytransactionDate(Matchers.any())).thenReturn(Arrays.asList(new Transaction()));
        when(authenticationService.isValidUser(Matchers.anyString(),Matchers.anyString())).thenReturn(true);
        this.mockMvc.perform(get("/transactions?day=2020-01-05")).andDo(print()).andExpect(status().isOk())
                .andExpect(ResultMatcher.matchAll());
    }

    @Test
    public void  getUserOperationsTest() throws Exception {
        when(transactionRepository.findTransactionBytransactionDate(Matchers.any())).thenReturn(Arrays.asList(new Transaction()));
        when(authenticationService.isValidUser(Matchers.anyString(),Matchers.anyString())).thenReturn(true);
        this.mockMvc.perform(get("/users/Kevin123?year=2020")).andDo(print()).andExpect(status().isOk())
                .andExpect(ResultMatcher.matchAll());
    }

    @Test
    public void getUserTransactionsTest() throws Exception {
        when(transactionRepository.findTransactionBytransactionDate(Matchers.any())).thenReturn(Arrays.asList(new Transaction()));
        when(authenticationService.isValidUser(Matchers.anyString(),Matchers.anyString())).thenReturn(true);
        this.mockMvc.perform(get("/transactions/Billing/users/Michelle420")).andDo(print()).andExpect(status().isOk())
                .andExpect(ResultMatcher.matchAll());
    }


}
