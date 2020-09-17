package com.jungguji.windbossgentimer.service;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.dungeon.DungeonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<Integer> findMainChannelByName(String name) {
        return this.dungeonRepository.findMainChannelByName(name);
    }

    public List<Integer> findSubChannelByNameAndMainChannel(String name, int mainChannel) {
        return this.dungeonRepository.findSubChannelByNameAndMainChannel(name, mainChannel);
    }
}
