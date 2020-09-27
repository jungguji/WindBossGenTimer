package com.jungguji.windbossgentimer.domain.channel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findByDungeonId(Integer id);
    List<Channel> findByDungeonIdAndMainChannel(Integer id, Integer mainChannel);
}
