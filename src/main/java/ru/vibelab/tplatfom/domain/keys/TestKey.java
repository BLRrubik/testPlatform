package ru.vibelab.tplatfom.domain.keys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class TestKey implements Serializable {
    // TODO: Генерация ID
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;
}
