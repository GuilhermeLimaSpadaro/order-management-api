package com.gspadaro.ordermanagerapi.repository;

import com.gspadaro.ordermanagerapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
