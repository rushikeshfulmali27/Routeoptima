package com.routeoptima.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "cargo_type")
    private String cargoType;

    private Double weight;

    @ManyToOne
    @JoinColumn(name = "source_port_id", nullable = false)
    private Port sourcePort;

    @ManyToOne
    @JoinColumn(name = "dest_port_id", nullable = false)
    private Port destinationPort;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
        if (status == null) {
            status = ShipmentStatus.PENDING;
        }
    }
}
