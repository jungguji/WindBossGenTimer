package com.jungguji.windbossgentimer.domain.channel;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.dungeon.DungeonRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
class ChannelRepositoryTest {

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

    private Channel createChannel(int main, int sub, Dungeon d) {
        Channel c = Channel.builder()
                .mainChannel(main)
                .subChannel(sub)
                .dungeon(d)
                .build();

        this.channelRepository.save(c);
        return c;
    }

    @Test
    void findByDungeonIdAndMainChannel() {
        //given
        Dungeon d = dungeons.get(1);

        d.addChannel(createChannel(1, 1, d));
        d.addChannel(createChannel(1, 2, d));
        d.addChannel(createChannel(1, 3, d));
        d.addChannel(createChannel(1, 4, d));
        d.addChannel(createChannel(2, 4, d));
        d.addChannel(createChannel(20, 7, d));

        //when
        List<Channel> whens = this.channelRepository.findByDungeonIdAndMainChannel(2, 1);

        //then
        assertEquals(4, whens.size());
        assertEquals(1, whens.get(0).getSubChannel());
        assertEquals(2, whens.get(1).getSubChannel());
        assertEquals(3, whens.get(2).getSubChannel());
        assertEquals(4, whens.get(3).getSubChannel());
    }
}