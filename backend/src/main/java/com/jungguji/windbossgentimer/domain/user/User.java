package com.jungguji.windbossgentimer.domain.user;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Dungeon> dungeons = new ArrayList<>();

    @Builder
    public User(String email, String password) {
        Assert.hasText(email, "email is required");
        Assert.hasText(password, "password is required");

        this.email = email;
        this.password = password;
    }

    public void addDungeon(Dungeon dungeon) {
        this.dungeons.add(dungeon);
    }
}
