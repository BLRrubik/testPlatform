package ru.vibelab.tplatfom.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vibelab.tplatfom.domain.keys.TestKey;

import javax.persistence.*;

@Entity
@Table(name = "test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @EmbeddedId
    private TestKey id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
}
