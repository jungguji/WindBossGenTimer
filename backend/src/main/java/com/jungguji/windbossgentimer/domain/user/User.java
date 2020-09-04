package com.jungguji.windbossgentimer.domain.user;

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
    private List<Dungeon> dungeons;

    @Builder
    public User(String email, String password, List<Dungeon> dungeons) {
        Assert.hasText(email, "email is required");
        Assert.hasText(password, "password is required");

        this.email = email;
        this.password = password;
        this.dungeons = dungeons;
    }
}
