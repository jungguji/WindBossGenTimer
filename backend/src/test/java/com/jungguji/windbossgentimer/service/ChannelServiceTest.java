package com.jungguji.windbossgentimer.service;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.channel.ChannelRepository;
import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.region.Region;
import com.jungguji.windbossgentimer.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
class ChannelServiceTest {

    @MockBean
    private ChannelRepository channelRepository;

    private ChannelService channelService;

    @BeforeEach
    void setUp() {
        channelService = new ChannelService(channelRepository);
    }

    @Test
    void findByDungeonIdAndMainChannel() {
        //given
        Dungeon d = Dungeon.builder()
                .region(Region.builder().name("aa").build())
                .name("test")
                .user(User.builder().email("e").password("1").build())
                .build();

        List<Channel> givens = Arrays.asList(
                createChannel(1, 2, d)
                , createChannel(1, 1, d)
                , createChannel(1, 20, d)
                , createChannel(1, 12, d)
        );

        given(this.channelRepository.findByDungeonIdAndMainChannel(any(), any())).willReturn(givens);

        //when
        List<Channel> whens = this.channelService.findByDungeonIdAndMainChannel(1, 1);

        //then
        for (int i = 0; i < whens.size(); i++) {
            Channel w = whens.get(i);
            Channel g = givens.get(i);

            assertEquals(g.getSubChannel(), w.getSubChannel());
        }
    }

    private Channel createChannel(int main, int sub, Dungeon d) {
        Channel c = Channel.builder()
                .mainChannel(main)
                .subChannel(sub)
                .dungeon(d)
                .build();

        given(this.channelRepository.save(any())).willReturn(c);
        return c;
    }
}