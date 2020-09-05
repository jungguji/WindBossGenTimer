package com.jungguji.windbossgentimer.domain.gentime;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Getter
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

    @Builder
    public GenTime(String bossName, LocalTime genTime, Dungeon dungeon) {
        Assert.hasText(bossName, "boss name is required");
        Assert.notNull(genTime, "gen time is required");
        Assert.notNull(dungeon, "dungeon is required");

        this.bossName = bossName;
        this.genTime = genTime;
        this.dungeon = dungeon;
    }
}