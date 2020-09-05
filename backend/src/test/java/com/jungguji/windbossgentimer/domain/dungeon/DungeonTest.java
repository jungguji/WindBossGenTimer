package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.gentime.GenTime;
import com.jungguji.windbossgentimer.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DungeonTest {

    private User user;
    private List<GenTime> genTimes;

    LocalTime genTime;
    String bossName;
    String bossName1;

    String email;
    @BeforeEach
    void setUp() {
        email = "test@naver.com";
        String pw = "testsuite";

        user = User.builder()
                .email(email)
                .password(pw)
                .build();

        String name = "전갈굴";
        int main = 1;
        int sub = 13;

        Dungeon dungeon = Dungeon.builder()
                .name(name)
                .mainChannel(main)
                .subChannel(sub)
                .user(user)
                .build();

        bossName = "도깨비왕";
        bossName1 = "힘쎈전갈";

        genTime = LocalTime.now();
        genTimes = Arrays.asList(
            GenTime.builder()
                .bossName(bossName)
                .genTime(genTime)
                .dungeon(dungeon)
                .build()
            , GenTime.builder()
                        .bossName(bossName1)
                        .genTime(genTime)
                        .dungeon(dungeon)
                        .build()
        );
    }

    @Test
    void 던전_생성() {
        //given

        String dname = "도깨비굴";
        int main = 1;
        int sub = 13;

        //when
        Dungeon dungeon = Dungeon.builder()
                .name(dname)
                .mainChannel(main)
                .subChannel(sub)
                .user(user)
                .genTimes(genTimes)
                .build();
        //then

        assertEquals(dname, dungeon.getName());
        assertEquals(main, dungeon.getMainChannel());
        assertEquals(sub, dungeon.getSubChannel());
        assertEquals(email, dungeon.getUser().getEmail());

        for (GenTime genTime : dungeon.getGenTimes()) {
            assertEquals(this.genTime, genTime.getGenTime());
        }

        assertEquals(bossName, dungeon.getGenTimes().get(0).getBossName());
        assertEquals(bossName1, dungeon.getGenTimes().get(1).getBossName());
    }

}