package ru.vibelab.tplatfom.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDTO {
    private String username;
    private String token;
    private String status;

    public AuthDTO(String status) {
        this.status = status;
    }

}
