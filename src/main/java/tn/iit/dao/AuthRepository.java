package tn.iit.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iit.entity.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByUsername(String username);
}
