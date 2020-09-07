package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.user.User;
import com.jungguji.windbossgentimer.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
class DungeonRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DungeonRepository dungeonRepository;

    User user;
    String email = "test@gmail.com";
    String pw = "pwpwpw";

    String dungeonName1 = "유령굴";
    String dungeonName2 = "산적굴";

    List<Dungeon> dungeons = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .email(email)
                .password(pw)
                .build();

        userRepository.save(user);

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

        dungeons = Arrays.asList(
                dungeon1
                , dungeon2
        );

        dungeonRepository.saveAll(dungeons);
    }

    @Test
    void 던전_리스트_가져오기() {
        //given
        //when
        List<Dungeon> whens = dungeonRepository.findAll();

        //then
        assertEquals(2, whens.size());

        for (int i = 0; i < 2; i++) {
            Dungeon given = dungeons.get(i);
            Dungeon when = whens.get(i);

            assertEquals(given.getName(), when.getName());
            assertEquals(given.getMainChannel(), when.getMainChannel());
            assertEquals(given.getSubChannel(), when.getSubChannel());
            assertEquals(given.getUser().getEmail(), when.getUser().getEmail());
        }


    }
}