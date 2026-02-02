package com.routeoptima.backend.controller;

import com.routeoptima.backend.dto.ShipmentDTO;
import com.routeoptima.backend.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @GetMapping
    public List<ShipmentDTO> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @PostMapping
    public ShipmentDTO createShipment(@RequestBody ShipmentDTO shipmentDTO) {
        return shipmentService.createShipment(shipmentDTO);
    }
}
