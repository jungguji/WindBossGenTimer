package com.jungguji.windbossgentimer.domain.dungeon;

import com.jungguji.windbossgentimer.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DungeonRepository extends JpaRepository<Dungeon, Long> {

    List<Dungeon> findByUser(User user);

    @Query(value=""
            + "SELECT        "
            + "   name       "
            + "FROM          "
            + "   Dungeon    "
            + "GROUP BY      "
            + "   name       ")
    List<String> findNameGroupByName();

    @Query(value = "SELECT mainChannel FROM Dungeon WHERE name =:name")
    List<Integer> findMainChannelByName(String name);
}
