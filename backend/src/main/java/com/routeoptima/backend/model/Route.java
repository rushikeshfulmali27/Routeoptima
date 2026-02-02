package com.routeoptima.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_port_id", nullable = false)
    private Port sourcePort;

    @ManyToOne
    @JoinColumn(name = "dest_port_id", nullable = false)
    private Port destinationPort;

    @Column(name = "distance_km", nullable = false)
    private Double distanceKm;

    @Column(name = "travel_time_hours")
    private Double travelTimeHours;

    @Column(nullable = false)
    private Double cost;

    @Column(name = "path_coordinates", columnDefinition = "TEXT")
    private String pathCoordinates; // JSON string of [[lat, lng], [lat, lng], ...]
}
