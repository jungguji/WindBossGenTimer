package com.jungguji.windbossgentimer.domain.region;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

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
