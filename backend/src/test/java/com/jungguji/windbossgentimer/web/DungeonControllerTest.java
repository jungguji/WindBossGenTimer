package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.user.User;
import com.jungguji.windbossgentimer.domain.user.UserRepository;
import com.jungguji.windbossgentimer.service.DungeonService;
import com.jungguji.windbossgentimer.web.dto.DungeonDTO;
import com.jungguji.windbossgentimer.web.dto.DungeonDTO.MainView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    DungeonController dungeonController;

    User user;
    String email = "test@gmail.com";
    String pw = "password";

    String dungeonName1 = "유령굴";
    String dungeonName2 = "산적굴";

    List<Dungeon> dungeons = new ArrayList<>();

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
    void 던전_리스트_JSON_리턴() throws Exception {
        //given
        Dungeon dungeon1 = Dungeon.builder()
                .name(dungeonName1)
                .mainChannel(1)
                .subChannel(1)
                .user(user)
                .build();

        Dungeon dungeon2 = Dungeon.builder()
                .name(dungeonName2)
                .mainChannel(1)
                .subChannel(2)
                .user(user)
                .build();

        Dungeon dungeon3 = Dungeon.builder()
                .name(dungeonName2)
                .mainChannel(2)
                .subChannel(1)
                .user(user)
                .build();

        Dungeon dungeon4 = Dungeon.builder()
                .name(dungeonName2)
                .mainChannel(3)
                .subChannel(11)
                .user(user)
                .build();

        dungeons = Arrays.asList(
                dungeon1
                , dungeon2
                , dungeon3
                , dungeon4
        );

        given(this.dungeonService.findAll()).willReturn(dungeons);

        //when
        final ResultActions action = mockMvc.perform(get("/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        MvcResult result = action.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}