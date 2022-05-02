package ru.vibelab.tplatfom.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDTO {
    @NotNull
    @NotEmpty
    private String username;


    @NotNull
    @NotEmpty
    private String password;
}