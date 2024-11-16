package org.api_civa.service;

import org.api_civa.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BrandService {
    Page<Brand> findAll(Pageable pageable);
    Optional<Brand> findById(Long id);
    Optional<Brand> findByName(String name);
    Brand save(Brand brand, Long id);
    void deleteById(Long id);
}
