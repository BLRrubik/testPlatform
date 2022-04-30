package ru.vibelab.tplatfom.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTestRequest {
    private String name;
    private String user;  // TODO: Заменить на UserRequest
}
