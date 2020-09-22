package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.service.ChannelService;
import com.jungguji.windbossgentimer.web.dto.channel.SubChannelListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChannelController {
    private final ChannelService channelService;

    @GetMapping("/dungeon/{dungeonId}/{main}")
    public List<SubChannelListResponseDTO> findByDungeonIdAndMainChannel(@PathVariable("dungeonId") Integer dungeonId
            , @PathVariable("main") Integer mainChannel) {
        List<Channel> channels = this.channelService.findByDungeonIdAndMainChannel(dungeonId, mainChannel);

        SubChannelListResponseDTO dto = new SubChannelListResponseDTO();
        List<SubChannelListResponseDTO> subChannelList = dto.convertChannelListToSubChannelListResponseList(channels);

        return subChannelList;
    }
}
