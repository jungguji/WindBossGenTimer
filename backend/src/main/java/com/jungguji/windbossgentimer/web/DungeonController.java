package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.service.DungeonService;
import com.jungguji.windbossgentimer.web.dto.DungeonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DungeonController {
    private final DungeonService dungeonService;

    @GetMapping(value="/main", produces = "application/json")
    public List<DungeonDTO.MainView> findNameByGroupName() {
        return DungeonDTO.MainView.toMainViewList(dungeonService.findNameByGroupName());
    }

    @GetMapping(value="/channel/main", produces = "application/json")
    public List<Integer> findMainChannelByName(@RequestParam("name") String name) {
        return this.dungeonService.findMainChannelByName(name);
    }

    @GetMapping(value="/channel/sub", produces = "application/json")
    public List<Integer> findMainChannelByName(@RequestParam("name") String name, @RequestParam("main") int main) {
        return this.dungeonService.findMainChannelByName(name);
    }
}
