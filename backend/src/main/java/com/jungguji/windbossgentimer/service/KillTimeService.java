package com.jungguji.windbossgentimer.service;

import com.jungguji.windbossgentimer.domain.boss.Boss;
import com.jungguji.windbossgentimer.domain.boss.BossRepository;
import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.channel.ChannelRepository;
import com.jungguji.windbossgentimer.domain.killtime.KillTime;
import com.jungguji.windbossgentimer.domain.killtime.KillTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class KillTimeService {
    private final KillTimeRepository killTimeRepository;
    private final ChannelRepository channelRepository;
    private final BossRepository bossRepository;

    public KillTime create(Long channelId, Long bossId, String killTimes) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new IllegalArgumentException("해당 채널이 존재하지 않습니다."));
        Boss boss = bossRepository.findById(bossId)
                .orElseThrow(() -> new IllegalArgumentException("해당 보스가 존재하지 않습니다."));

        KillTime killTime = KillTime.builder()
                .channel(channel)
                .boss(boss)
                .killTime(LocalTime.parse(killTimes))
                .build();

        killTimeRepository.save(killTime);

        return killTime;
    }

    public KillTime update(Long killTimeId, Long channelId, Long bossId, String killTimes) {
        KillTime killTime = killTimeRepository.findById(killTimeId)
                .orElse(create(channelId, bossId, killTimes));

        killTime.update(LocalTime.parse(killTimes));

        return killTime;
    }
}
