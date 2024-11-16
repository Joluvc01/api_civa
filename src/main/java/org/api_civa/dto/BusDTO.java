package org.api_civa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BusDTO implements Serializable {

        @Schema(hidden = true)
        private Long id;
        private int busNumber;
        private String plate;
        private LocalDate creationDate;
        private int capacity;
        private String characteristics;
        private Long brand_id;
        private Boolean is_active;
}
