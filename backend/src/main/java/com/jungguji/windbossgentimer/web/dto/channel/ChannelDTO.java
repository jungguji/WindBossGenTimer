package com.jungguji.windbossgentimer.web.dto.channel;

import com.jungguji.windbossgentimer.domain.channel.Channel;
import com.jungguji.windbossgentimer.domain.killtime.KillTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChannelDTO {

    @Getter
    @NoArgsConstructor
    public static class ChannelListResponse {
        private Integer mainChannel;
        private List<SubChannel> subChannels;

        @Builder
        public ChannelListResponse(int mainChannel, List<SubChannel> subChannels) {
            this.mainChannel = mainChannel;
            this.subChannels = subChannels;
        }

        public List<ChannelListResponse> convert(List<Channel> channels) {
            Set<Integer> set = new HashSet<>();

            List<ChannelListResponse> list = new ArrayList<>();

            List<SubChannel> subChannels = new ArrayList<>();
            int main = 0;

            for (Channel c : channels) {
                if (set.add(c.getMainChannel())){
                    if (main != 0) {
                        list.add(ChannelListResponse.builder()
                                .mainChannel(main)
                                .subChannels(subChannels)
                                .build());
                    }

                    main = c.getMainChannel();
                    subChannels = new ArrayList<>();
                }

                subChannels.add(new SubChannel(c.getId(), c.getSubChannel()));
            }

            return list;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SubChannel {
        private Integer id;
        private Integer subChannel;

        public SubChannel(int id, int subChannel) {
            this.id = id;
            this.subChannel = subChannel;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class responseBossList {
        private Integer rowId;
        private Integer killTimeId;
        private String bossName;
        private Map<String, Integer> genTime;
        private Map<String, Integer> killTime;

        public responseBossList(KillTime killTime, int rowId) {
            Integer killTimeId = killTime.getId();
            String bossName = killTime.getBoss().getName();
            LocalTime kTime = killTime.getKillTime();
            LocalTime genTime = killTime.getBoss().getGenTime();

            Map<String, Integer> genTimeMap  = new HashMap<>();
            genTimeMap.put("hour", genTime.getHour());
            genTimeMap.put("minute", genTime.getMinute());
            genTimeMap.put("second", genTime.getSecond());

            Map<String, Integer>  killTimeMap = new HashMap<>();
            killTimeMap.put("hour", genTime.getHour());
            killTimeMap.put("minute", genTime.getMinute());
            killTimeMap.put("second", genTime.getSecond());

            this.rowId = rowId;
            this.killTimeId = killTimeId;
            this.bossName = bossName;
            this.genTime = genTimeMap;
            this.killTime = killTimeMap;
        }
    }
}
