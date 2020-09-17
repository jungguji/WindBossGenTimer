package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.gentime.GenTime;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Dungeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private final String name;
    private final Integer mainChannel;
    private final Integer subChannel;
    private LocalTime killTime;

    @ManyToOne
    @JoinColumn(name="user_id")
    private final User user;

    @OneToMany(mappedBy = "dungeon")
    private List<GenTime> genTimes = new ArrayList<>();

    @Builder
    public Dungeon(String name, Integer mainChannel, Integer subChannel, User user) {
        Assert.notNull(name, "name is required");
        Assert.notNull(mainChannel, "main channel is required");
        Assert.notNull(subChannel, "sub channel is required");
        Assert.notNull(user, "user is required");

        this.name = name;
        this.mainChannel = mainChannel;
        this.subChannel = subChannel;
        this.user = user;
    }

    public void addGenTime(GenTime genTime) {
        this.genTimes.add(genTime);
    }
}
