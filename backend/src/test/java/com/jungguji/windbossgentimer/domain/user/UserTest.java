package com.jungguji.windbossgentimer.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void User_생성_테스트() {
        //given
        String email = "test@naver.com";
        String pw = "testtestset";

        //when
        User user = User.builder()
                .email(email)
                .password(pw)
                .build();

        //then
        assertEquals(email, user.getEmail());
        assertEquals(pw, user.getPassword());
    }
}