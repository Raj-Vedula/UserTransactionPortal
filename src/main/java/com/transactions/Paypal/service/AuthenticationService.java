package com.transactions.Paypal.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

     public boolean isValidUser(String pathUser,String headerUser){
         if(pathUser.equals(headerUser))
             return true;
         else return false;
     }
}
