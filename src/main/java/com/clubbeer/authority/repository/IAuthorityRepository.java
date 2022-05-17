package com.clubbeer.authority.repository;

import com.clubbeer.authority.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByName(String name);
}
