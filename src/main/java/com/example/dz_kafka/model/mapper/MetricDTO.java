package com.example.dz_kafka.model.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class MetricDTO {
    private String name;
    private List<MeasurementsDTO> measurements;
    @JsonIgnore
    private String baseUnit;
    @JsonIgnore
    private String description;
    @JsonIgnore
    private List<?> availableTags;
}
