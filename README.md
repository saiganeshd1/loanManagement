# Loan Management REST API

A Spring Boot-based RESTful web service for a fintech application to manage customer onboarding, loan applications, repayments, and loan status tracking.

---

## 🚀 Features

- ✅ Create and manage customers
- ✅ Apply for loans
- ✅ View loan details
- ✅ Make repayments
- ✅ Track loan status
- ✅ Field validations and proper error handling
- ✅ Unit & integration test coverage for all modules

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL (local)
- JUnit 5 & Mockito
- Maven
- Lombok

---

## 📦 Modules

### 1. User
- `POST /api/users` – Create customer
- Fields: Name, Email, Phone, DOB, Aadhaar, PAN

### 2. Loan
- `POST /api/loans` – Apply for loan
- `GET /api/loans/user/{userId}` – Get loans by user ID

### 3. payment
- `POST /api/payment` – Make a payment

---

## ✅ Validations

- Name, Email, and Phone are required for customers
- Aadhar: 12-digit numeric
- PAN: 10-character alphanumeric format (e.g., ABCDE1234F)
- Loan amount must be > 0
- Repayment amount must be > 0
- Payment date cannot be in future

---

## 🧪 Testing

- Unit and integration test coverage using JUnit & MockMvc
- Valid and invalid test scenarios included

To run tests:
```bash
mvn test

POSTMAN CURL :

**1. Create User**

curl --location 'localhost:8080/api/users/create' \
--header 'Content-Type: application/json' \
--data-raw '{
     "name" : "sai",
    "email" : "saiganesh@gmail.com",
    "phone" : "9154093135",
    "dateOfBirth" : "2002-08-18",
    "panNo" : "LWFPS1017G",
    "aadhaarNo" : "123456789992"
}'

**2. Apply for Loan**

curl --location 'localhost:8080/api/loans/create' \
--header 'Content-Type: application/json' \
--data '{
     "userId" : 2,
    "amount" : 20000,
    "termInMonths" : 15,
    "interestRate" : 10
}'

**3. Make Payment**

curl --location 'localhost:8080/api/loans/6/payment' \
--header 'Content-Type: application/json' \
--data '{
    "amount" : 1000,
    "paymentDate" : "2025-05-04",
    "paymentStatus" : "COMPLETED"
}'

**4. Get All Loans for a Customer**

curl -X GET http://localhost:8080/api/loans/user/1

5. Get Loan by ID**

curl -X GET http://localhost:8080/api/loans/1

6. Get Loan Status

curl -X GET http://localhost:8080/api/loans/1/status





