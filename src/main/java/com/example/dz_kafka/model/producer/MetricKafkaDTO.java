package com.example.dz_kafka.model.producer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetricKafkaDTO {
    private String date;
    private String time;
    private Double cpu;
    private Double memory;
}
