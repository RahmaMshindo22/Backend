package com.example.seafood;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {
        // Custom query methods if needed
}
