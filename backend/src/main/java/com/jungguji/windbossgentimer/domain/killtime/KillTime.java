package com.jungguji.windbossgentimer.domain.killtime;

import com.jungguji.windbossgentimer.domain.boss.Boss;
import com.jungguji.windbossgentimer.domain.channel.Channel;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Entity
public class KillTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="channel_id")
    private final Channel channel;

    @ManyToOne
    @JoinColumn(name="boss_id")
    private final Boss boss;

    private LocalTime killTime;

    public KillTime(Channel channel, Boss boss, LocalTime killTime) {
        Assert.notNull(channel, "channel is required");
        Assert.notNull(boss, "boss is required");
        Assert.notNull(killTime, "kill time is required");

        this.channel = channel;
        this.boss = boss;
        this.killTime = killTime;
    }
}
