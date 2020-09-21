package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DungeonTest {

    private User user;

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
                .user(user)
                .build();

        bossName = "도깨비왕";
        bossName1 = "힘쎈전갈";
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
                .user(user)
                .build();
        //then

        assertEquals(dname, dungeon.getName());
        assertEquals(email, dungeon.getUser().getEmail());
    }

}