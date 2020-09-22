package com.jungguji.windbossgentimer.service;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.dungeon.DungeonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DungeonService {

    private final DungeonRepository dungeonRepository;

    public List<Dungeon> findAll() {
        return this.dungeonRepository.findAll();
    }

    public List<String> findNameByGroupName() {
        return this.dungeonRepository.findNameGroupByName();
    }

    public List<Integer> findMainChannelById(Integer id) {
        List<Integer> mainChannels = this.dungeonRepository.findMainChannelById(id);
        Collections.sort(mainChannels);

        return mainChannels;
    }

}
