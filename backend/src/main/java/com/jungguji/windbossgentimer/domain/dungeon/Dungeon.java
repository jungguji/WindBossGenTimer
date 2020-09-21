package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.boss.Boss;
import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.region.Region;
import com.jungguji.windbossgentimer.domain.user.User;
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
public class Dungeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="region_id")
    private final Region region;

    @ManyToOne
    @JoinColumn(name="user_id")
    private final User user;

    private final String name;

    @OneToMany(mappedBy = "dungeon")
    private List<Channel> channels = new ArrayList<>();

    @OneToMany(mappedBy = "dungeon")
    private List<Boss> bosses = new ArrayList<>();

    @Builder
    public Dungeon(String name, Region region, User user) {
        Assert.notNull(name, "name is required");
        Assert.notNull(region, "region is required");
        Assert.notNull(user, "user is required");

        this.name = name;
        this.region = region;
        this.user = user;
    }

    public void addChannel(Channel channel) {
        this.channels.add(channel);
    }

    public void addBoss(Boss boss) {
        this.bosses.add(boss);
    }
}
