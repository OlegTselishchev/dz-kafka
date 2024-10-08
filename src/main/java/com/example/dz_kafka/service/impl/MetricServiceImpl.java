package com.example.dz_kafka.service.impl;

import com.example.dz_kafka.model.entity.Metrics;
import com.example.dz_kafka.repository.MetricRepository;
import com.example.dz_kafka.service.MetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricServiceImpl implements MetricService {

    private final MetricRepository repository;

    @Override
    public List<Metrics> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Metrics> findById(Integer id) {
        return repository.findById(id);
    }
}
