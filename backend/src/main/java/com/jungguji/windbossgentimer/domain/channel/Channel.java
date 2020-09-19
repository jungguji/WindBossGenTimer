package com.jungguji.windbossgentimer.domain.channel;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.killtime.KillTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="dungeon_id")
    private final Dungeon dungeon;

    private final Integer mainChannel;
    private final Integer subChannel;

    @OneToMany(mappedBy = "channel")
    private List<KillTime> killTimes = new ArrayList<>();

    @Builder
    public Channel(Integer mainChannel, Integer subChannel, Dungeon dungeon) {
        Assert.notNull(mainChannel, "main channel is required");
        Assert.notNull(subChannel, "sub channel is required");
        Assert.notNull(dungeon, "dungeon is required");

        this.mainChannel = mainChannel;
        this.subChannel = subChannel;
        this.dungeon = dungeon;
    }

    public void addKillTime(KillTime killTime) {
        this.killTimes.add(killTime);
    }
}
