package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.service.DungeonService;
import com.jungguji.windbossgentimer.web.dto.dungeon.DungeonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DungeonController {
    private final DungeonService dungeonService;

    @GetMapping("/dungeon/{id}")
    public List<Integer> findMainChannelById(@PathVariable("id") Integer id) {
        return this.dungeonService.findMainChannelById(id);
    }
}
