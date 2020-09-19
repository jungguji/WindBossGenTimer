package com.jungguji.windbossgentimer.domain.channel;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.gentime.GenTime;
import com.jungguji.windbossgentimer.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private final User user;

    @ManyToOne
    @JoinColumn(name="dungeon_id")
    private final Dungeon dungeon;

    private final Integer mainChannel;
    private final Integer subChannel;
    private LocalTime killTime;

    @OneToMany(mappedBy = "channel")
    private List<GenTime> genTimes = new ArrayList<>();

    @Builder
    public Channel(Integer mainChannel, Integer subChannel, User user, Dungeon dungeon, List<GenTime> genTimes) {
        Assert.notNull(mainChannel, "main channel is required");
        Assert.notNull(subChannel, "sub channel is required");
        Assert.notNull(user, "user is required");
        Assert.notNull(dungeon, "dungeon is required");

        this.mainChannel = mainChannel;
        this.subChannel = subChannel;
        this.user = user;
        this.dungeon = dungeon;
        this.genTimes = genTimes;
    }

    public void addGenTime(GenTime genTime) {
        this.genTimes.add(genTime);
    }
}
