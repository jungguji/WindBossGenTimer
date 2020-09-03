package com.jungguji.windbossgentimer.domain.gentime;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
@Entity
public class GenTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private final String bossName;
    private final LocalTime genTime;

    @ManyToOne
    @JoinColumn(name="dungeon_id")
    private final Dungeon dungeon;
}
