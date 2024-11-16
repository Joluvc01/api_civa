package org.api_civa.util;

import org.api_civa.dto.BusDTO;
import org.api_civa.entity.Brand;
import org.api_civa.entity.Bus;
import org.api_civa.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusMapper {

    private final BrandService brandService;

    @Autowired
    public BusMapper(BrandService brandService) {
        this.brandService = brandService;
    }

    public BusDTO toBusDTO(Bus bus) {

        if (bus == null) {
            return null;
        }

        Long brand_id = brandService.findById(bus.getBrand().getId())
                .map(Brand::getId)
                .orElse(null);

        return new BusDTO(
                bus.getId(),
                bus.getBusNumber(),
                bus.getPlate(),
                bus.getCreationDate(),
                bus.getCapacity(),
                bus.getCharacteristics(),
                brand_id,
                bus.getIs_active()
        );
    }

    public Bus toBus(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setBusNumber(busDTO.getBusNumber());
        bus.setPlate(busDTO.getPlate());
        bus.setCreationDate(busDTO.getCreationDate());
        bus.setCapacity(busDTO.getCapacity());
        bus.setCharacteristics(busDTO.getCharacteristics());
        bus.setIs_active(busDTO.getIs_active());

        if (busDTO.getBrand_id() != null) {
            Brand brand = brandService.findById(busDTO.getBrand_id())
                    .orElseThrow(() -> new IllegalArgumentException("La marca con ID " + busDTO.getBrand_id() + " no existe"));
            bus.setBrand(brand);
        } else {
            throw new IllegalArgumentException("El ID de la marca no puede ser nulo");
        }

        return bus;
    }

}
