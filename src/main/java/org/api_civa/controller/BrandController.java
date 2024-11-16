package org.api_civa.controller;

import org.api_civa.entity.Brand;
import org.api_civa.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(brandService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return brandService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.save(brand, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Brand brand, @PathVariable Long id) {
        return ResponseEntity.ok(brandService.save(brand, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
