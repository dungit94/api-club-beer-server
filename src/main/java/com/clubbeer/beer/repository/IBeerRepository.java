package com.clubbeer.beer.repository;

import com.clubbeer.beer.entity.Beer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBeerRepository extends JpaRepository<Beer, Long> {
}
