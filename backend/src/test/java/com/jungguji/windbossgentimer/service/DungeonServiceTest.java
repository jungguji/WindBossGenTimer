package com.jungguji.windbossgentimer.service;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.dungeon.DungeonRepository;
import com.jungguji.windbossgentimer.domain.region.Region;
import com.jungguji.windbossgentimer.domain.region.RegionRepository;
import com.jungguji.windbossgentimer.domain.user.User;
import com.jungguji.windbossgentimer.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
class DungeonServiceTest {

    @MockBean
    DungeonRepository dungeonRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RegionRepository regionRepository;

    DungeonService service;

    User user;
    String email = "test@gmail.com";
    String pw = "password";

    String dungeonName1 = "유령굴";
    String dungeonName2 = "산적굴";

    List<Dungeon> dungeons = new ArrayList<>();

    Region region;

    @BeforeEach
    void setUp() {
        service = new DungeonService(dungeonRepository);

        user = User.builder()
                .email(email)
                .password(pw)
                .build();

        userRepository.save(user);

        region = createRegion("부여성");

    }

    private Region createRegion(String name) {
        Region region = Region.builder()
                .name(name)
                .build();


        given(this.regionRepository.save(region)).willReturn(region);

        return region;
    }

    @Test
    void 던전_전체_검색() {
        //given
        Dungeon dungeon1 = Dungeon.builder()
                .name(dungeonName1)
                .user(user)
                .region(region)
                .build();

        Dungeon dungeon2 = Dungeon.builder()
                .name(dungeonName2)
                .user(user)
                .region(region)
                .build();

        Dungeon dungeon3 = Dungeon.builder()
                .name(dungeonName2)
                .user(user)
                .region(region)
                .build();

        Dungeon dungeon4 = Dungeon.builder()
                .name(dungeonName2)
                .user(user)
                .region(region)
                .build();

        dungeons = Arrays.asList(
                dungeon1
                , dungeon2
                , dungeon3
                , dungeon4
        );

        given(this.dungeonRepository.findAll()).willReturn(dungeons);

        //when
        List<Dungeon> whens = service.findAll();

        //then
        for (int i = 0; i < whens.size(); i++) {
            Dungeon d = dungeons.get(i);
            Dungeon w = whens.get(i);

            assertEquals(d.getName(), w.getName());
            assertEquals(d.getUser(), w.getUser());
        }
    }
}