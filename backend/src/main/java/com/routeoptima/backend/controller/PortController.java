package com.routeoptima.backend.controller;

import com.routeoptima.backend.dto.PortDTO;
import com.routeoptima.backend.service.PortService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ports")
@CrossOrigin(origins = "*") // For development simplicity
@RequiredArgsConstructor
public class PortController {

    private final PortService portService;

    @GetMapping
    public List<PortDTO> getAllPorts() {
        return portService.getAllPorts();
    }

    @PostMapping
    public PortDTO createPort(@RequestBody PortDTO portDTO) {
        return portService.createPort(portDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePort(@PathVariable Long id) {
        portService.deletePort(id);
        return ResponseEntity.ok().build();
    }
}
