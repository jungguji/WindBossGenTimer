package com.jungguji.windbossgentimer.domain.gentime;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.dungeon.DungeonRepository;
import com.jungguji.windbossgentimer.domain.user.User;
import com.jungguji.windbossgentimer.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
class GenTimeRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DungeonRepository dungeonRepository;

    User user;
    String email = "test@gmail.com";
    String pw = "password";

    String dungeonName1 = "유령굴";

    Dungeon dungeon1;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .email(email)
                .password(pw)
                .build();

        userRepository.save(user);

        dungeon1 = Dungeon.builder()
                .name(dungeonName1)
                .mainChannel(1)
                .subChannel(1)
                .user(user)
                .build();

        dungeonRepository.save(dungeon1);
    }

    @Test
    void 생성() {
        //given
        String name = "힘쎈전갈";
        LocalTime givenLocal = LocalTime.of(3,0);
        //when
        GenTime genTime = GenTime.builder()
                .bossName(name)
                .genTime(givenLocal)
                .dungeon(dungeon1)
                .build();

        //then

        assertEquals(name, genTime.getBossName());
        assertEquals(givenLocal, genTime.getGenTime());
        assertEquals("유령굴", genTime.getDungeon().getName());
    }
}