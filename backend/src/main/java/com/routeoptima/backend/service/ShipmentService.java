package com.routeoptima.backend.service;

import com.routeoptima.backend.dto.ShipmentDTO;
import com.routeoptima.backend.model.Port;
import com.routeoptima.backend.model.Shipment;
import com.routeoptima.backend.model.ShipmentStatus;
import com.routeoptima.backend.repository.PortRepository;
import com.routeoptima.backend.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final PortRepository portRepository;

    public List<ShipmentDTO> getAllShipments() {
        return shipmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ShipmentDTO createShipment(ShipmentDTO dto) {
        Shipment shipment = new Shipment();
        shipment.setClientName(dto.getClientName());
        shipment.setCargoType(dto.getCargoType());
        shipment.setWeight(dto.getWeight());
        
        Port source = portRepository.findById(dto.getSourcePortId()).orElseThrow();
        Port dest = portRepository.findById(dto.getDestPortId()).orElseThrow();
        
        shipment.setSourcePort(source);
        shipment.setDestinationPort(dest);
        shipment.setStatus(ShipmentStatus.PENDING);
        // Dates handled by PrePersist or manually
        shipment.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());
        
        Shipment saved = shipmentRepository.save(shipment);
        return convertToDTO(saved);
    }

    private ShipmentDTO convertToDTO(Shipment shipment) {
        ShipmentDTO dto = new ShipmentDTO();
        dto.setId(shipment.getId());
        dto.setClientName(shipment.getClientName());
        dto.setCargoType(shipment.getCargoType());
        dto.setWeight(shipment.getWeight());
        dto.setSourcePortId(shipment.getSourcePort().getId());
        dto.setDestPortId(shipment.getDestinationPort().getId());
        dto.setStatus(shipment.getStatus());
        dto.setCreationDate(shipment.getCreationDate());
        dto.setExpectedDeliveryDate(shipment.getExpectedDeliveryDate());
        return dto;
    }
}
