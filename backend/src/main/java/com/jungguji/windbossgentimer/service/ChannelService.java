package com.jungguji.windbossgentimer.service;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.channel.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChannelService {
    private final ChannelRepository channelRepository;

    public List<Channel> findByDungeonId(int id) {
        List<Channel> channels = this.channelRepository.findByDungeonId(id);

        channels.sort((c1, c2) -> c1.getMainChannel() > c2.getMainChannel() ? 1 : c1.getMainChannel() < c2.getMainChannel() ? -1 : c1.getSubChannel().compareTo(c2.getSubChannel()));
        return channels;
    }

    public List<Channel> findByDungeonIdAndMainChannel(int id, int mainChannel) {
        List<Channel> channels = this.channelRepository.findByDungeonIdAndMainChannel(id, mainChannel);

        channels.sort(Comparator.comparing(Channel::getSubChannel));
        return channels;
    }
}

