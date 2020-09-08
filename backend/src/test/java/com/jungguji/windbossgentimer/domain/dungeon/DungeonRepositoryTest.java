package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.gentime.GenTime;
import com.jungguji.windbossgentimer.domain.gentime.GenTimeRepository;
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

    @Autowired
    GenTimeRepository genTimeRepository;

    User user;
    String email = "test@gmail.com";
    String pw = "password";

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

        dungeonRepository.saveAll(dungeons);
    }

    @Test
    void 던전_리스트_전체_가져오기() {
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

    @Test
    void 유저별_던전_리스트_가져오기() {
        //given
        String user1Email = "jgji@gmail.com";
        String user1pw = "test123";

        User user1 = User.builder()
                .email(user1Email)
                .password(user1pw)
                .build();

        userRepository.save(user1);

        String dname = "비밀세작의 집";
        int mc = 20;
        int sm = 9;

        Dungeon given = Dungeon.builder()
                .name(dname)
                .mainChannel(mc)
                .subChannel(sm)
                .user(user1)
                .build();

        dungeonRepository.save(given);

        String name = "힘쎈전갈";
        LocalTime givenLocal = LocalTime.of(3,0);
        GenTime genTime = GenTime.builder()
                .bossName(name)
                .genTime(givenLocal)
                .dungeon(given)
                .build();

        genTimeRepository.save(genTime);

        given.addGenTime(genTime);

        //when
        List<Dungeon> whens = dungeonRepository.findByUser(user1);

        //then
        assertEquals(1, whens.size());

        Dungeon when = whens.get(0);

        assertEquals(given.getName(), when.getName());
        assertEquals(given.getMainChannel(), when.getMainChannel());
        assertEquals(given.getSubChannel(), when.getSubChannel());
        assertEquals(given.getUser().getEmail(), when.getUser().getEmail());
        assertEquals(name, when.getGenTimes().get(0).getBossName());
    }

    @Test
    void 던전_별_메인채널_리스트_가져오기() {
        //given
        //when
        List<Integer> whens = dungeonRepository.findMainChannelByName(dungeonName2);

        //then
        assertEquals(3, whens.size());
        assertEquals(1, whens.get(0));
        assertEquals(2, whens.get(1));
        assertEquals(3, whens.get(2));
    }
}