package com.tp1p3.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplaylistRepo extends JpaRepository<Splaylist, Integer> {
}