package com.routeoptima.backend.service;

import com.routeoptima.backend.dto.RouteDTO;
import com.routeoptima.backend.model.Port;
import com.routeoptima.backend.model.Route;
import com.routeoptima.backend.repository.PortRepository;
import com.routeoptima.backend.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final PortRepository portRepository;

    public List<RouteDTO> getAllRoutes() {
        return routeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public RouteDTO createRoute(RouteDTO dto) {
        Route route = new Route();
        Port source = portRepository.findById(dto.getSourcePortId()).orElseThrow();
        Port dest = portRepository.findById(dto.getDestPortId()).orElseThrow();

        route.setSourcePort(source);
        route.setDestinationPort(dest);
        route.setDistanceKm(dto.getDistanceKm());
        route.setTravelTimeHours(dto.getTravelTimeHours());
        route.setCost(dto.getCost());
        route.setPathCoordinates(dto.getPathCoordinates());

        return convertToDTO(routeRepository.save(route));
    }

    private RouteDTO convertToDTO(Route route) {
        RouteDTO dto = new RouteDTO();
        dto.setId(route.getId());
        dto.setSourcePortId(route.getSourcePort().getId());
        dto.setDestPortId(route.getDestinationPort().getId());
        dto.setDistanceKm(route.getDistanceKm());
        dto.setTravelTimeHours(route.getTravelTimeHours());
        dto.setCost(route.getCost());
        dto.setPathCoordinates(route.getPathCoordinates());
        return dto;
    }
}
