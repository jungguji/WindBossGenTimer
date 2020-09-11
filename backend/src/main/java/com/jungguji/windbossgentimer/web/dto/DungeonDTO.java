package com.jungguji.windbossgentimer.web.dto;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DungeonDTO {

    @Getter
    public static class MainView {
        private Integer id;
        private String name;

        public MainView(Dungeon dungeon) {
            this.id = dungeon.getId();
            this.name = dungeon.getName();
        }

        public static List<MainView> toMainViewList(List<Dungeon> dungeons) {
            List<MainView> list = new ArrayList<>();
            for (Dungeon d : dungeons) {
                list.add(new MainView(d));
            }

            return list;
        }
    }
}
