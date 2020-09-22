package com.jungguji.windbossgentimer.web.dto.channel;

import com.jungguji.windbossgentimer.domain.boss.Boss;
import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.killtime.KillTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class SubChannelListResponseDTO {
    private Integer subChannel;
    private List<BossAndKillTime> boss = new ArrayList<>();

    @Getter
    public static class BossAndKillTime {
        private String name;
        private LocalTime killTime;

        public BossAndKillTime(String name, LocalTime killTime) {
            this.name = name;
            this.killTime = killTime;
        }
    }

    @Builder
    public SubChannelListResponseDTO(Integer subChannel, List<BossAndKillTime> boss) {
        this.subChannel = subChannel;
        this.boss = boss;
    }

    public SubChannelListResponseDTO convertChannelToSubChannelListResponse(Channel channel) {
        int subChannel = channel.getSubChannel();
        Map<String, LocalTime> boss = new HashMap<>();

        List<KillTime> killTimes = channel.getKillTimes();
        for (KillTime killTime : killTimes) {
            boss.put(killTime.getBoss().getName(), killTime.getKillTime());
        }

        List<BossAndKillTime> bossAndKillTimes = new ArrayList<>();
        List<String> names = channel.getDungeon().getBosses().stream().map(Boss::getName).collect(Collectors.toList());
        for (String name : names) {
            LocalTime localTime = LocalTime.of(0, 0);
            if (boss.containsKey(name)) {
                localTime = boss.get(name);
            }

            bossAndKillTimes.add(new BossAndKillTime(name, localTime));
        }

        return SubChannelListResponseDTO.builder()
                .subChannel(subChannel)
                .boss(bossAndKillTimes)
                .build();
    }

    public List<SubChannelListResponseDTO> convertChannelListToSubChannelListResponseList(List<Channel> channels) {
        List<SubChannelListResponseDTO> list = new ArrayList<>();
        for (Channel c : channels) {
            list.add(new SubChannelListResponseDTO().convertChannelToSubChannelListResponse(c));
        }

        return list;
    }
}
