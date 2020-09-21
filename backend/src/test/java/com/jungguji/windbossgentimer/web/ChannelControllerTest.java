package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.channel.ChannelRepository;
import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
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

    private ChannelController channelController;

    @MockBean
    DungeonService dungeonService;

    @BeforeEach
    void setUp() {
        this.channelController = new ChannelController(channelService);
    }

    @Test
    void findByDungeonIdAndMainChannel() throws Exception {
        //given
        Dungeon d = Dungeon.builder()
                .region(Region.builder().name("aa").build())
                .name("test")
                .user(User.builder().email("e").password("1").build())
                .build();

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
                .andExpect(jsonPath("$[0].mainChannel", is(1)))
                .andExpect(jsonPath("$[0].subChannel", is(1)))
                .andExpect(jsonPath("$[1].mainChannel", is(1)))
                .andExpect(jsonPath("$[1].subChannel", is(2)))
                .andExpect(jsonPath("$[2].mainChannel", is(1)))
                .andExpect(jsonPath("$[2].subChannel", is(12)))
                .andExpect(jsonPath("$[3].subChannel", is(20)))
                .andReturn();
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