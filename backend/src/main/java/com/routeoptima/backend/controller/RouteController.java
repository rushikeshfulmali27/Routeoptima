package com.routeoptima.backend.controller;

import com.routeoptima.backend.dto.RouteDTO;
import com.routeoptima.backend.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    public List<RouteDTO> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @PostMapping
    public RouteDTO createRoute(@RequestBody RouteDTO routeDTO) {
        return routeService.createRoute(routeDTO);
    }
}
