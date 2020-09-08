package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DungeonRepository extends JpaRepository<Dungeon, Long> {

    List<Dungeon> findByUser(User user);

    @Query(value = "SELECT mainChannel FROM Dungeon WHERE name =:name")
    List<Integer> findMainChannelByName(String name);
}
