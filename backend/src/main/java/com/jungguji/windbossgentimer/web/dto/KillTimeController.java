package com.jungguji.windbossgentimer.web.dto;

import com.jungguji.windbossgentimer.service.KillTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KillTimeController {
    private final KillTimeService killTimeService;

    @PutMapping("/dungeon/{dungeonId}/{channelId}")
    public ResponseEntity update(@PathVariable("channelId") Long channelId
            , @RequestParam("killTimeId") String killTimeId
            , @RequestParam("bossId") Long bossId
            , @RequestParam("killTime") Long killTimes) {

//        killTimeService.update(killTimeId, channelId, bossId, killTimes);
//
        return ResponseEntity.ok().build();
    }
}
