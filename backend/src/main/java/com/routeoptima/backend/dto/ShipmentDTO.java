package com.routeoptima.backend.dto;

import com.routeoptima.backend.model.ShipmentStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ShipmentDTO {
    private Long id;
    private String clientName;
    private String cargoType;
    private Double weight;
    private Long sourcePortId;
    private Long destPortId;
    private ShipmentStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime expectedDeliveryDate;
}
