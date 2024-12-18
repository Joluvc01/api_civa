package org.api_civa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    @Schema(hidden = true)
    private int statusCode;

    @Schema(hidden = true)
    private String error;

    @Schema(hidden = true)
    private String message;

    @Schema(hidden = true)
    private String token;

    private String username;

    private String password;
}

