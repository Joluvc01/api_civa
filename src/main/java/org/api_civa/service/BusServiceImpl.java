package org.api_civa.service;

import jakarta.persistence.EntityNotFoundException;
import org.api_civa.dto.BusDTO;
import org.api_civa.entity.Bus;
import org.api_civa.repository.BusRepository;
import org.api_civa.util.BusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService{

    private final BusRepository busRepository;
    private final BusMapper busMapper;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, BusMapper busMapper) {
        this.busRepository = busRepository;
        this.busMapper = busMapper;
    }

    @Override
    public Page<BusDTO> findAll(Pageable pageable) {
        Page<Bus> busesPage = busRepository.findAll(pageable);

        List<BusDTO> busDTOS = busesPage.getContent().stream()
                .map(busMapper::toBusDTO)
                .toList();

        return new PageImpl<>(busDTOS, pageable, busesPage.getTotalElements());
    }

    @Override
    public Optional<Bus> findById(Long id) {
        return busRepository.findById(id);
    }

    @Override
    public Optional<Bus> findByBusNumber(int busNumber) {
        return busRepository.findByBusNumber(busNumber);
    }

    @Override
    public Optional<Bus> findByPlate(String plate) {
        return busRepository.findByPlate(plate);
    }

    @Override
    public Bus save(BusDTO busDTO, Long id) {
        Optional<Bus> existingPlate = busRepository.findByPlate(busDTO.getPlate());
        if (existingPlate.isPresent() && !existingPlate.get().getId().equals(id)) {
            throw new IllegalArgumentException("La placa ya está en uso");
        }
        Optional<Bus> existingBus_number = busRepository.findByBusNumber(busDTO.getBusNumber());
        if (existingBus_number.isPresent() && !existingBus_number.get().getId().equals(id)) {
            throw new IllegalArgumentException("El número de bus está en uso");
        }

        if (id != null) {
            Bus existingBus = busRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("El bus no existe"));

            Bus updatedBus = busMapper.toBus(busDTO);
            updatedBus.setId(existingBus.getId());
            updatedBus.setCreationDate(existingBus.getCreationDate());
            return busRepository.save(updatedBus);
        } else {
            LocalDate gendate = LocalDate.now(ZoneId.of("America/Lima"));
            busDTO.setCreationDate(gendate);
            return busRepository.save(busMapper.toBus(busDTO));
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Bus> existingBus = busRepository.findById(id);
        if (existingBus.isEmpty()) {
            throw new EntityNotFoundException("El bus no existe");
        }
        busRepository.deleteById(id);
    }
}
