package com.jungguji.windbossgentimer.web.dto;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import com.jungguji.windbossgentimer.domain.region.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class RegionDTO {

    @Getter
    @NoArgsConstructor
    public static class MainViewResponse {
        private String name;
        private List<MainViewDungeonAttribute> dungeons = new ArrayList<>();

        public MainViewResponse(String name, List<MainViewDungeonAttribute> dungeons) {
            this.name = name;
            this.dungeons = dungeons;
        }

        public MainViewResponse(Region region) {
            String name = region.getName();
            List<Dungeon> dungeons = region.getDungeons();

            List<MainViewDungeonAttribute> attributes = new ArrayList<>();
            for (Dungeon d : dungeons) {
                attributes.add(new MainViewDungeonAttribute(d.getId(), d.getName()));
            }

            this.name = name;
            this.dungeons = attributes;
        }

        public List<MainViewResponse> convertRegionListToMainViewResponseList(List<Region> regions) {
            List<MainViewResponse> mainViewResponses = new ArrayList<>();
            for (Region r : regions) {
                mainViewResponses.add(new MainViewResponse(r));
            }

            return mainViewResponses;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class MainViewDungeonAttribute {
        private Integer id;
        private String name;

        public MainViewDungeonAttribute(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
