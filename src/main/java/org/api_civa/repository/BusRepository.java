package org.api_civa.repository;

import org.api_civa.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByBusNumber(int busNumber);
    Optional<Bus> findByPlate(String plate);
}
