package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.service.DungeonService;
import com.jungguji.windbossgentimer.web.dto.DungeonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DungeonController {
    private final DungeonService dungeonService;

    @GetMapping(value="/all", produces = "application/json")
    @ResponseBody
    public List<DungeonDTO.MainView> findAll() {
        return DungeonDTO.MainView.toMainViewList(dungeonService.findAll());
    }
}
