package br.com.gspadaro.ordermanagerapi.repository;

import br.com.gspadaro.ordermanagerapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
