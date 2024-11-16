package org.api_civa.service;

import org.api_civa.entity.Brand;
import org.api_civa.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public Brand save(Brand brand, Long id) {
        Optional<Brand> optionalBrand = brandRepository.findByName(brand.getName());
        if (optionalBrand.isPresent()){
            throw new IllegalArgumentException("Nombre de marca en uso");
        }
        if (id != null){
            Brand existingBrand = brandRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada"));
            existingBrand.setName(brand.getName());
            return brandRepository.save(existingBrand);
        } else {
            return brandRepository.save(brand);
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Brand> existingBrand = brandRepository.findById(id);
        if (existingBrand.isEmpty()){
            throw new IllegalArgumentException("La marca no existe");
        }
        brandRepository.deleteById(id);
    }
}
