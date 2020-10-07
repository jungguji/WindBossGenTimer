package com.jungguji.windbossgentimer.service;

import com.jungguji.windbossgentimer.domain.boss.Boss;
import com.jungguji.windbossgentimer.domain.boss.BossRepository;
import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.channel.ChannelRepository;
import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.killtime.KillTime;
import com.jungguji.windbossgentimer.domain.killtime.KillTimeRepository;
import com.jungguji.windbossgentimer.domain.region.Region;
import com.jungguji.windbossgentimer.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
class KillTimeServiceTest {

    @MockBean
    ChannelRepository channelRepository;

    @MockBean
    BossRepository bossRepository;

    @MockBean
    KillTimeRepository killTimeRepository;

    KillTimeService killTimeService;

    @BeforeEach
    void setUp() {
        this.killTimeService = new KillTimeService(killTimeRepository, channelRepository, bossRepository);
    }

    @Test
    void create() {
        //given
        Optional<Channel> channel = Optional.ofNullable(getChannel());
        Optional<Boss> boss = Optional.ofNullable(getBoss());

        given(this.channelRepository.findById((Long) any())).willReturn(channel);
        given(this.bossRepository.findById((Long) any())).willReturn(boss);
        //when

        KillTime when = killTimeService.create(1L, 1L, "14:00:00");

        //then
        assertThat(when).isNotNull();

        assertEquals(LocalTime.of(14,0,0), when.getKillTime());
        assertEquals(when.getBoss().getName(), boss.get().getName());
    }

    @Test
    void update() {
        //given
        Optional<Channel> channel = Optional.ofNullable(getChannel());
        Optional<Boss> boss = Optional.ofNullable(getBoss());

        KillTime killTime = KillTime.builder()
                .channel(channel.get())
                .boss(boss.get())
                .killTime(LocalTime.parse("14:00:00"))
                .build();

        given(killTimeRepository.findById((Long) any())).willReturn(Optional.ofNullable(killTime));
        given(this.channelRepository.findById((Long) any())).willReturn(channel);
        given(this.bossRepository.findById((Long) any())).willReturn(boss);

        //when
        KillTime when = this.killTimeService.update(1L,1L, 1L, "14:00:00");

        //then
        assertThat(when).isNotNull();

        assertEquals(LocalTime.of(14,0,0), killTime.getKillTime());
        assertEquals(when.getBoss().getName(), killTime.getBoss().getName());
    }

    private Channel getChannel() {
        return Channel.builder()
                .mainChannel(1)
                .subChannel(2)
                .dungeon(getDungeon())
                .build();
    }

    private Dungeon getDungeon() {
        return Dungeon.builder()
                .user(getUser())
                .region(getRegion())
                .name("흉가")
                .build();
    }

    private User getUser() {
        return User.builder()
                .email("test@gmail.com")
                .password("qweqwe")
                .build();
    }

    private Region getRegion() {
        return Region.builder()
                .name("부여성")
                .build();
    }

    private Boss getBoss() {
        return Boss.builder()
                .dungeon(getDungeon())
                .name("힘쎈 노인")
                .genTime(LocalTime.of(1,0,0))
                .build();
    }
}