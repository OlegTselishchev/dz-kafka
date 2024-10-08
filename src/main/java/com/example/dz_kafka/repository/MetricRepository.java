package com.example.dz_kafka.repository;

import com.example.dz_kafka.model.entity.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MetricRepository extends JpaRepository<Metrics, Integer> {

    @Query(value = "select * from Metrics;", nativeQuery = true)
    List<Metrics> findAll();

    @Query(value = "select * from Metrics m where m.id = ?1;", nativeQuery = true)
    Optional<Metrics> findById(Integer id);
}
