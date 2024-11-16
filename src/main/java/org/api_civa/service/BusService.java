package org.api_civa.service;

import org.api_civa.dto.BusDTO;
import org.api_civa.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BusService {
    Page<BusDTO> findAll(Pageable pageable);
    Optional<Bus> findById(Long id);
    Optional<Bus> findByBusNumber(int busNumber);
    Optional<Bus> findByPlate(String plate);
    Bus save(BusDTO busDTO, Long id);
    void deleteById(Long id);
}
