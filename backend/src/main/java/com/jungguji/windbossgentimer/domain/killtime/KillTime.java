package com.jungguji.windbossgentimer.domain.killtime;

import com.jungguji.windbossgentimer.domain.boss.Boss;
import com.jungguji.windbossgentimer.domain.channel.Channel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
public class KillTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="channel_id")
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boss_id")
    private Boss boss;

    private LocalTime killTime;

    @Builder
    public KillTime(Channel channel, Boss boss, LocalTime killTime) {
        Assert.notNull(channel, "channel is required");
        Assert.notNull(boss, "boss is required");
        Assert.notNull(killTime, "kill time is required");

        this.channel = channel;
        this.boss = boss;
        this.killTime = killTime;
    }

    public void update(LocalTime killTime) {
        this.killTime = killTime;
    }
}
