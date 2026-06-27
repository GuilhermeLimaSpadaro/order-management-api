package com.gspadaro.ordermanagerapi.repositories;

import com.gspadaro.ordermanagerapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
