package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.domain.user.User;
import com.jungguji.windbossgentimer.domain.user.UserRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DungeonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @MockBean
    DungeonService dungeonService;

    @MockBean
    private ChannelController channelController;

    DungeonController dungeonController;

    User user;
    String email = "test@gmail.com";
    String pw = "password";

    String dungeonName1 = "유령굴";
    String dungeonName2 = "산적굴";

    @BeforeEach
    void setUp() {
        dungeonController = new DungeonController(dungeonService);
        user = User.builder()
                .email(email)
                .password(pw)
                .build();

        userRepository.save(user);
    }

    @Test
    void 던전이름_리스트_JSON_리턴() throws Exception {
        //given
        List<String> given = Arrays.asList(
                dungeonName1
                , dungeonName2
        );

        given(this.dungeonService.findNameByGroupName()).willReturn(given);

        //when
        final ResultActions action = mockMvc.perform(get("/main")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        MvcResult result = action.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "[{\"name\":\"유령굴\"},{\"name\":\"산적굴\"}]";
        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    void findMainChannelById() throws Exception {
        //given
        Integer id = 1;

        List<Integer> givens = Arrays.asList(
                1,2,3,4,5,6,7,13,20
        );

        given(this.dungeonService.findMainChannelById(any())).willReturn(givens);

        //when
        final ResultActions action = this.mockMvc.perform(get("/dungeon/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        MvcResult result = action.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "[1,2,3,4,5,6,7,13,20]";
        assertEquals(expected, result.getResponse().getContentAsString());
    }
}