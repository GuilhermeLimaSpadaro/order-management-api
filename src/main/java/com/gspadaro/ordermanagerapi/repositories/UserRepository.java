package com.gspadaro.ordermanagerapi.repositories;

import com.gspadaro.ordermanagerapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
