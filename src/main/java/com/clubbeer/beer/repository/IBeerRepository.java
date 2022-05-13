package com.clubbeer.beer.repository;

import com.clubbeer.beer.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBeerRepository extends JpaRepository<Beer, Long> {
}
