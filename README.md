# UserTransactionPortal
Application that exposes rest endpoints to get user transaction information <br /> 

This project implements Spring Boot, Rest API, Spring security, Spring MVC, Spring data JPA and  MYSQL. <br /> 

PaypalApplication.java is the starting point for the application <br />
There is are 4 sub packages at this level <br />
- Controller <br />
- Models <br />
- Repository and <br />
- Service <br />

JPARepository implementation TransactionRepository provides methods and Query annotations for <br />
JPQL and native SQL Queries. <br/>

Authentication is done in Authentication service. <br/>

Pom.xml has dependency on jpa , spring security and mysql.Properties file has MYSQL connection information and encrypted password <br />

Pricing/Charging
  In all these scenarios..Lazy loading based security checks could be placed for pricing and charging purposes.
- Providing information on various types of payment transactions done on a specific day by all users
     This service call is admin based/restricted access service call.
     So users could be charged by taking into consideration the paginated access to data.
     After first 4 pages , user could be charged for accessing more pages.
     Price check validation could be placed before retrieving lazy loaded data.
     In addition to pagination based paywall,Pricing could be set based on
     1) number of requests
     2) number of customers
     3) number of transactions
     
-  Providing information specifically to a particular user ID on the basis of year etc.
   Since this service is only for a specific user while looking at their own data, Pricing should be minimal.
    Retrieval of Older records can be pagination based charging
    
- Providing information based on particular transaction type for a particular user
   Since the root endpoint here is transactions...users could access transaction from other users..so
   user access is limited until they see their own transactions. Transaction based data filtering is an additional feature.
   

Unit test
  Test file package structure is same as the actual files.
  Testing is applicable mainly for Controller and Service class in this application.
   









