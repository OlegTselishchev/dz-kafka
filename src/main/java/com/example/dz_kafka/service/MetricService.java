package com.example.dz_kafka.service;

import com.example.dz_kafka.model.entity.Metrics;

import java.util.List;
import java.util.Optional;

public interface MetricService {
    List<Metrics> findAll();
    Optional<Metrics> findById(Integer id);
}
