package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.gentime.GenTime;
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

    private final String name;

    @OneToMany(mappedBy = "dungeon")
    private List<Channel> channels = new ArrayList<>();

    @Builder
    public Dungeon(String name, Region region, List<Channel> channels) {
        Assert.notNull(name, "name is required");
        Assert.notNull(region, "region is required");

        this.name = name;
        this.region = region;
        this.channels =channels;
    }

    public void addChannel(Channel channel) {
        this.channels.add(channel);
    }
}
