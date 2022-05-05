package ru.vibelab.tplatfom.DTO.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestResultShortDTO {
    private Long id;
    private Long score;
    private TestShortDTO test;
}
