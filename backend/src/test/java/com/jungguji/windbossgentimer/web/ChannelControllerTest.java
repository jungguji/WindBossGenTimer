package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.domain.boss.Boss;
import com.jungguji.windbossgentimer.domain.boss.BossRepository;
import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.channel.ChannelRepository;
import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.dungeon.DungeonRepository;
import com.jungguji.windbossgentimer.domain.killtime.KillTime;
import com.jungguji.windbossgentimer.domain.region.Region;
import com.jungguji.windbossgentimer.domain.user.User;
import com.jungguji.windbossgentimer.service.ChannelService;
import com.jungguji.windbossgentimer.service.DungeonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ChannelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelService channelService;

    @MockBean
    private ChannelRepository channelRepository;

    @MockBean
    private DungeonRepository dungeonRepository;

    @MockBean
    private BossRepository bossRepository;

    private ChannelController channelController;

    @MockBean
    DungeonService dungeonService;

    @BeforeEach
    void setUp() {
        this.channelController = new ChannelController(channelService);
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

    private KillTime createKillTime(Channel c, Boss b) {

        KillTime k = KillTime.builder()
                .channel(c)
                .boss(b)
                .killTime(LocalTime.now())
                .build();

        return k;
    }

    @Test
    void testFindByDungeonIdAndMainChannel() throws Exception {
        //given
        Dungeon d = Dungeon.builder()
                .region(Region.builder().name("aa").build())
                .name("전갈굴")
                .user(User.builder().email("e").password("1").build())
                .build();

        dungeonRepository.save(d);

        Boss boss = Boss.builder()
                .dungeon(d)
                .name("힘쎈 전갈")
                .build();
        Boss boss1 = Boss.builder()
                .dungeon(d)
                .name("힘쎈 가재")
                .build();
        Boss boss2 = Boss.builder()
                .dungeon(d)
                .name("힘쎈 전가재")
                .build();

        bossRepository.save(boss);
        bossRepository.save(boss1);
        bossRepository.save(boss2);

        d.addBoss(boss);
        d.addBoss(boss1);
        d.addBoss(boss2);

        List<Channel> givens = Arrays.asList(
                createChannel(1, 1, d)
                , createChannel(1, 2, d)
                , createChannel(1, 12, d)
                , createChannel(1, 20, d)
        );

        given(this.channelService.findByDungeonIdAndMainChannel(1, 1)).willReturn(givens);

        //when
        final ResultActions action = this.mockMvc.perform(get("/dungeon/1/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        MvcResult result = action.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].subChannel", is(1)))
                .andExpect(jsonPath("$[0].boss[0].name", is("힘쎈 전갈")))
                .andExpect(jsonPath("$[0].boss[0].killTime", is("00:00:00")))
                .andExpect(jsonPath("$[0].boss[1].name", is("힘쎈 가재")))
                .andExpect(jsonPath("$[0].boss[2].name", is("힘쎈 전가재")))
                .andReturn();
    }
}