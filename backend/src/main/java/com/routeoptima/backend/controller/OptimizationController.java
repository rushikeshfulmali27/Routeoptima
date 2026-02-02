package com.routeoptima.backend.controller;

import com.routeoptima.backend.dto.RouteDTO;
import com.routeoptima.backend.service.DijkstraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/optimize")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OptimizationController {

    private final DijkstraService dijkstraService;

    @GetMapping("/shortest-path")
    public List<RouteDTO> getShortestPath(
            @RequestParam Long sourceId,
            @RequestParam Long destId,
            @RequestParam(defaultValue = "1000") Double weight,
            @RequestParam(defaultValue = "Standard") String productType) {
        return dijkstraService.findShortestPath(sourceId, destId, weight, productType);
    }
}
