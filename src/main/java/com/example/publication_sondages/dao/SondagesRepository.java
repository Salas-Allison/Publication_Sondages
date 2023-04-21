package com.example.publication_sondages.dao;

import com.example.publication_sondages.entity.Sondages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SondagesRepository extends JpaRepository<Sondages, Long> {
}
