package com.jungguji.windbossgentimer.domain.user;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Getter
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private final String email;
    private final String password;

    @OneToMany(mappedBy = "user")
    private List<Channel> channels = new ArrayList<>();

    @Builder
    public User(String email, String password, List<Channel> channels) {
        Assert.hasText(email, "email is required");
        Assert.hasText(password, "password is required");

        this.email = email;
        this.password = password;
        this.channels = channels;
    }

    public void addChannel(Channel channel) {
        this.channels.add(channel);
    }
}
