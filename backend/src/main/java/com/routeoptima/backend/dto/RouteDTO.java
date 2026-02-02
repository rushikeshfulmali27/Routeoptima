package com.routeoptima.backend.dto;

import lombok.Data;

@Data
public class RouteDTO {
    private Long id;
    private Long sourcePortId;
    private Long destPortId;
    private Double distanceKm;
    private Double travelTimeHours;
    private Double cost;
    private String pathCoordinates;
}
