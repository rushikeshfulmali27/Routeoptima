package com.routeoptima.backend.dto;

import lombok.Data;

@Data
public class PortDTO {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double capacity;
    private String country;
}
