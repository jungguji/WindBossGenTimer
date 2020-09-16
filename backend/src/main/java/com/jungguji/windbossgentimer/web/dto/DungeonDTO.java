package com.jungguji.windbossgentimer.web.dto;

import com.jungguji.windbossgentimer.domain.dungeon.Dungeon;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DungeonDTO {

    @Getter
    public static class MainView {
        private String name;

        public MainView(String name) {
            this.name = name;
        }

        public static List<MainView> toMainViewList(List<String> dungeons) {
            List<MainView> list = new ArrayList<>();
            for (String name : dungeons) {
                list.add(new MainView(name));
            }

            return list;
        }
    }
}
