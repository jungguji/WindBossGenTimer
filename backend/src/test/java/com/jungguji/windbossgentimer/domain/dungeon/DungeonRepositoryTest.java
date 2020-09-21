package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.channel.ChannelRepository;
import com.jungguji.windbossgentimer.domain.region.Region;
import com.jungguji.windbossgentimer.domain.region.RegionRepository;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
class DungeonRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DungeonRepository dungeonRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    ChannelRepository channelRepository;

    User user;
    String email = "test@gmail.com";
    String pw = "password";

    String dungeonName1 = "유령굴";
    String dungeonName2 = "산적굴";
    String dungeonName3 = "인형굴";
    String dungeonName4 = "말왕굴";

    List<Dungeon> dungeons = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .email(email)
                .password(pw)
                .build();

        userRepository.save(user);

        Region region1 = createRegion("국내성");
        Region region2 = createRegion("산적소굴");
        Region region3 = createRegion("12지신");

        Dungeon dungeon1 = Dungeon.builder()
                .name(dungeonName1)
                .region(region1)
                .user(user)
                .build();

        Dungeon dungeon2 = Dungeon.builder()
                .name(dungeonName2)
                .region(region2)
                .user(user)
                .build();

        Dungeon dungeon3 = Dungeon.builder()
                .name(dungeonName3)
                .region(region1)
                .user(user)
                .build();

        Dungeon dungeon4 = Dungeon.builder()
                .name(dungeonName4)
                .region(region3)
                .user(user)
                .build();

        dungeons.add(dungeon1);
        dungeons.add(dungeon2);
        dungeons.add(dungeon3);
        dungeons.add(dungeon4);

        dungeonRepository.saveAll(dungeons);
    }

    private Region createRegion(String name) {
        Region region = Region.builder()
                .name(name)
                .build();


        this.regionRepository.save(region);
        return region;
    }

    @Test
    void 던전_리스트_전체_가져오기() {
        //given
        //when
        List<Dungeon> whens = dungeonRepository.findAll();

        //then
        assertEquals(4, whens.size());

        for (int i = 0; i < 2; i++) {
            Dungeon given = dungeons.get(i);
            Dungeon when = whens.get(i);

            assertEquals(given.getName(), when.getName());
            assertEquals(given.getRegion(), when.getRegion());
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
                .user(user1)
                .build();

        dungeonRepository.save(given);

        String name = "힘쎈전갈";
        LocalTime givenLocal = LocalTime.of(3,0);

        //when
        List<Dungeon> whens = dungeonRepository.findByUser(user1);

        //then
        assertEquals(1, whens.size());

        Dungeon when = whens.get(0);

        assertEquals(given.getName(), when.getName());
        assertEquals(given.getUser().getEmail(), when.getUser().getEmail());
    }

    @Test
    void 메인화면_던전_리스트_이름만() {
        //given
        //when
        List<String> whens = dungeonRepository.findNameGroupByName();

        //then
        assertEquals(2, whens.size());
        assertThat(whens).contains(dungeonName1)
                .contains(dungeonName2);
    }

    @Test
    void 던전_별_메인채널_리스트_가져오기() {
        //given
        int main = 1;
        int sub = 7;

        for (Dungeon d : dungeons) {
            d.addChannel(createChannel(main, sub, d));
        }

        Dungeon d = dungeons.get(1);

        d.addChannel(createChannel(2, sub, d));
        d.addChannel(createChannel(3, sub, d));
        d.addChannel(createChannel(4, sub, d));

        this.dungeonRepository.saveAll(dungeons);
        //when

        List<Integer> whens = this.dungeonRepository.findMainChannelById(2);
        //then

        assertThat(whens).isNotIn(5)
                .contains(1,2,3,4);
    }

    private Channel createChannel(int main, int sub, Dungeon d) {
        Channel c = Channel.builder()
                .mainChannel(main)
                .subChannel(sub)
                .dungeon(d)
                .build();

        this.channelRepository.save(c);

        return c;
    }
}