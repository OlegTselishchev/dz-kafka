package com.example.dz_kafka.service;

import com.example.dz_kafka.model.producer.MetricKafkaDTO;


public interface MetricProducerService {
    MetricKafkaDTO getMetric();
}
