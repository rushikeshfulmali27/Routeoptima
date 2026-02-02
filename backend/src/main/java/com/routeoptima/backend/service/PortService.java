package com.routeoptima.backend.service;

import com.routeoptima.backend.dto.PortDTO;
import com.routeoptima.backend.model.Port;
import com.routeoptima.backend.repository.PortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortService {

    private final PortRepository portRepository;

    public List<PortDTO> getAllPorts() {
        return portRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PortDTO createPort(PortDTO portDTO) {
        Port port = new Port();
        port.setName(portDTO.getName());
        port.setLatitude(portDTO.getLatitude());
        port.setLongitude(portDTO.getLongitude());
        port.setCapacity(portDTO.getCapacity());
        port.setCountry(portDTO.getCountry());
        Port savedPort = portRepository.save(port);
        return convertToDTO(savedPort);
    }
    
    public void deletePort(Long id) {
        portRepository.deleteById(id);
    }

    private PortDTO convertToDTO(Port port) {
        PortDTO dto = new PortDTO();
        dto.setId(port.getId());
        dto.setName(port.getName());
        dto.setLatitude(port.getLatitude());
        dto.setLongitude(port.getLongitude());
        dto.setCapacity(port.getCapacity());
        dto.setCountry(port.getCountry());
        return dto;
    }
}
