package com.routeoptima.backend.service;

import com.routeoptima.backend.dto.RouteDTO;
import com.routeoptima.backend.model.Route;
import com.routeoptima.backend.repository.PortRepository;
import com.routeoptima.backend.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DijkstraService {

    private final RouteRepository routeRepository;
    private final PortRepository portRepository;

    public List<RouteDTO> findShortestPath(Long sourcePortId, Long destPortId, Double weight, String productType) {
        // Fetch all routes
        List<Route> allRoutes = routeRepository.findAll();

        // Multiplier based on product type
        double typeMultiplier = switch (productType) {
            case "Fragile" -> 1.5;
            case "Hazardous" -> 2.5;
            case "Perishable" -> 1.8;
            default -> 1.0;
        };

        // Build Graph
        Map<Long, List<Route>> adj = new HashMap<>();
        for (Route route : allRoutes) {
            adj.computeIfAbsent(route.getSourcePort().getId(), k -> new ArrayList<>()).add(route);
        }

        // Dijkstra's
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.weight));
        Map<Long, Double> costs = new HashMap<>();
        Map<Long, Route> previousEdge = new HashMap<>();
        Set<Long> visited = new HashSet<>();

        costs.put(sourcePortId, 0.0);
        pq.add(new Node(sourcePortId, 0.0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            Long currentId = current.id;

            if (visited.contains(currentId))
                continue;
            visited.add(currentId);

            if (currentId.equals(destPortId))
                break;

            List<Route> edges = new ArrayList<>();
            edges.addAll(adj.getOrDefault(currentId, Collections.emptyList()));

            for (Route r : allRoutes) {
                if (r.getDestinationPort().getId().equals(currentId)) {
                    edges.add(r);
                }
            }

            for (Route edge : edges) {
                Long neighborId;
                if (edge.getSourcePort().getId().equals(currentId)) {
                    neighborId = edge.getDestinationPort().getId();
                } else {
                    neighborId = edge.getSourcePort().getId();
                }

                if (!visited.contains(neighborId)) {
                    // Dynamic Cost Calculation
                    // Formula: (Distance * 0.1) + (Weight * Distance * 0.005 * TypeMultiplier) +
                    // BaseRouteCost
                    double dynamicCost = (edge.getDistanceKm() * 0.1)
                            + (weight * edge.getDistanceKm() * 0.005 * typeMultiplier)
                            + edge.getCost();

                    double newCost = costs.get(currentId) + dynamicCost;
                    if (newCost < costs.getOrDefault(neighborId, Double.MAX_VALUE)) {
                        costs.put(neighborId, newCost);
                        previousEdge.put(neighborId, edge);
                        pq.add(new Node(neighborId, newCost));
                    }
                }
            }
        }

        // Reconstruct path
        List<RouteDTO> path = new ArrayList<>();
        Long current = destPortId;

        if (!previousEdge.containsKey(current) && !current.equals(sourcePortId)) {
            return Collections.emptyList();
        }

        while (current != null && !current.equals(sourcePortId)) {
            Route edge = previousEdge.get(current);
            if (edge == null)
                break;

            // Recalculate cost for the DTO to show the user the specific segment cost
            double segmentCost = (edge.getDistanceKm() * 0.1)
                    + (weight * edge.getDistanceKm() * 0.005 * typeMultiplier)
                    + edge.getCost();

            RouteDTO dto = convertToDTO(edge);
            dto.setCost(segmentCost); // Update DTO with dynamic cost

            if (edge.getSourcePort().getId().equals(current)) {
                dto.setPathCoordinates(reverseJsonCoordinates(dto.getPathCoordinates()));
            }

            path.add(dto);

            if (edge.getDestinationPort().getId().equals(current)) {
                current = edge.getSourcePort().getId();
            } else {
                current = edge.getDestinationPort().getId();
            }
        }
        Collections.reverse(path);
        return path;
    }

    private RouteDTO convertToDTO(Route route) {
        RouteDTO dto = new RouteDTO();
        dto.setId(route.getId());
        dto.setSourcePortId(route.getSourcePort().getId());
        dto.setDestPortId(route.getDestinationPort().getId());
        dto.setDistanceKm(route.getDistanceKm());
        dto.setTravelTimeHours(route.getTravelTimeHours());
        // Base cost initially, will be overridden
        dto.setCost(route.getCost());
        dto.setPathCoordinates(route.getPathCoordinates());
        return dto;
    }

    private String reverseJsonCoordinates(String json) {
        if (json == null || json.isEmpty())
            return json;
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            double[][] points = mapper.readValue(json, double[][].class);
            List<double[]> pointList = Arrays.asList(points);
            Collections.reverse(pointList);
            return mapper.writeValueAsString(pointList);
        } catch (Exception e) {
            return json; // Fallback
        }
    }

    private record Node(Long id, Double weight) {
    }
}
