package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.gentime.GenTime;
import com.jungguji.windbossgentimer.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Dungeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private final User user;

    @OneToMany(mappedBy = "dungeon")
    private final List<GenTime> genTimes;

    private final Integer mainChannel;
    private final Integer subChannel;
    private LocalTime killTime;

}
