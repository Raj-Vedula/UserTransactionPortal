package com.transactions.Paypal.controller;

import com.transactions.Paypal.repository.TransactionRepository;
import com.transactions.Paypal.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
public class TransactionController {
   @Autowired
   public TransactionRepository transactionRepository;

   @Autowired
   public AuthenticationService authenticationService;

   /*
     various types of payment transactions (Invoicing, billing, subscriptions)
     done on a specific day

     Pricing/Charging - This service call is admin based/restricted access service call.
     So users could be charged by taking into consideration the paginated access to data.
     After first 4 pages , user could be charged for accessing more pages.

     Price check validation could be placed before retrieving lazy loaded data.

     In addition to pagination based paywall,Pricing could be set based on
     1) number of requests
     2) number of customers
     3) number of transactions
    */
   @GetMapping("/transactions")
   @PreAuthorize("permitAll()") //PreAuthorize could taken inhasRole('ROLE_ADMIN') for admin users authorization
    public ResponseEntity getDailyTransactions(@RequestParam("day") String day, @PathVariable(value="userId",required = false) String userId, @RequestHeader("Username") String user)
   {
      if(day==null || user==null)
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      if(Optional.ofNullable(userId).isPresent() ?!authenticationService.isValidUser(userId,user):false)
         return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate localDate = LocalDate.parse(day, formatter);
      return ResponseEntity.ok(transactionRepository.findTransactionBytransactionDate(localDate));
   }

   /*
   information specifically to a particular user ID
       on the basis of year etc.
    Pricing/Charging - Since this service is only for a specific user while looking at their own data, Pricing should be minimal.
    Retrieval of Older records can be pagination based charging
    */
   @GetMapping("/users/{userId}")
   @PreAuthorize("permitAll()")
    public ResponseEntity getUserOperations(@PathVariable("userId") String userId, @RequestParam("year") String year,
                                                         @RequestHeader("Username") String user)
   {
      if(userId==null || year==null || user==null)
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      if(!authenticationService.isValidUser(userId,user))
         return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
      return ResponseEntity.ok(transactionRepository.findTransactionForUserByYear(Integer.parseInt(year),userId));
   }

   /*
    Information based on particular transaction type
   for a particular user

   Pricing/Charging - since the root endpoint here is transactions...users could access transaction from other users..so
   user access is limited until they see their own transactions.
   Transaction based data filtering is an additional feature.
    */
   @GetMapping("/transactions/{transactionType}/users/{userId}")
   @PreAuthorize("permitAll()")
    public ResponseEntity getUserTransactions(@PathVariable("userId") String userId, @PathVariable("transactionType") String transactionType,
                                                     @RequestHeader("Username") String user)
   {
      if(userId==null || transactionType==null || user==null)
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      if(!userId.equals(user))
         return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return  ResponseEntity.ok(transactionRepository.findTransactionByTypeAndUser(transactionType,userId));
   }


}
