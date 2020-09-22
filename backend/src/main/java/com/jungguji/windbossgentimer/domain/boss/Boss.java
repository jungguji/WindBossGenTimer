package com.jungguji.windbossgentimer.domain.boss;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.killtime.KillTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="dungeon_id")
    private Dungeon dungeon;

    private String name;
    private LocalTime genTime;

    @OneToMany(mappedBy = "boss")
    private List<KillTime> killTimes = new ArrayList<>();

    @Builder
    public Boss(Dungeon dungeon, String name, LocalTime genTime) {
        Assert.notNull(dungeon, "dungeon is required");
        Assert.notNull(name, "name is required");

        this.dungeon = dungeon;
        this.name = name;
        this.genTime = genTime;
    }

    public void addKillTime(KillTime killTime) {
        this.killTimes.add(killTime);
    }
}
