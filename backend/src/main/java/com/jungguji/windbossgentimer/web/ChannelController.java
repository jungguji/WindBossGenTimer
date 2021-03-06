package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.killtime.KillTime;
import com.jungguji.windbossgentimer.service.ChannelService;
import com.jungguji.windbossgentimer.web.dto.channel.ChannelDTO.ChannelListResponse;
import com.jungguji.windbossgentimer.web.dto.channel.ChannelDTO.responseBossList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChannelController {
    private final ChannelService channelService;

    @GetMapping("/dungeon/{dungeonId}")
    public List<ChannelListResponse> findByDungeonId(@PathVariable("dungeonId") Integer dungeonId) {
        List<Channel> channels = this.channelService.findByDungeonId(dungeonId);
        return new ChannelListResponse().convert(channels);
    }

    @GetMapping("/dungeon/{dungeonId}/{id}")
    public List<responseBossList> findById(@PathVariable("id") Integer id) {
        Channel channel = this.channelService.findById(id);

        List<responseBossList> responseBossLists = new ArrayList<>();

        List<KillTime> killTimes = channel.getKillTimes();
        int i = 0;
        for (KillTime k : killTimes) {
            responseBossLists.add(new responseBossList(k, i++));
        }

        return responseBossLists;
    }
}
