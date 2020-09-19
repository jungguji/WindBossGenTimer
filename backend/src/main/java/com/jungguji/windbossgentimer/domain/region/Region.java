package com.jungguji.windbossgentimer.domain.region;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.gentime.GenTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private final String name;

    @OneToMany(mappedBy = "region")
    private List<Dungeon> dungeons = new ArrayList<>();

    @Builder
    public Region(String name) {
        Assert.notNull(name, "name is required");

        this.name = name;
    }

    public void addDungeon(Dungeon dungeon) {
        this.dungeons.add(dungeon);
    }
}
