package ru.vibelab.tplatfom.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestRequest {
    private String name;
    private Set<BundledQuestionRequest> questions;
}
