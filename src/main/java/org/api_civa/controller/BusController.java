package org.api_civa.controller;

import org.api_civa.dto.BusDTO;
import org.api_civa.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(busService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return busService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BusDTO busDTO) {
        return ResponseEntity.ok(busService.save(busDTO, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody BusDTO busDTO, @PathVariable Long id) {
        return ResponseEntity.ok(busService.save(busDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        busService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
