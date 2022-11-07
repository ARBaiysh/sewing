package kg.ssb.sewing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponseDTO {
    private boolean success;
    private String token;
}

