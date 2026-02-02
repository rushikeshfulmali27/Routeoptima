package com.routeoptima.backend.repository;

import com.routeoptima.backend.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findBySourcePortId(Long sourcePortId);
}
