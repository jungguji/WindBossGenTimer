CREATE TABLE wind_boss_gen_timer.channel
(
    `id`            INT    NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `dungeon_id`    INT    NOT NULL    COMMENT '던전 아아디',
    `main_channel`  INT    NOT NULL    COMMENT '주 채널',
    `sub_channel`   INT    NOT NULL    COMMENT '서브 채널',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.channel COMMENT '채널';

ALTER TABLE wind_boss_gen_timer.channel
    ADD CONSTRAINT FK_channel_dungeon_id_dungeon_id FOREIGN KEY (dungeon_id)
        REFERENCES wind_boss_gen_timer.dungeon (id) ON DELETE RESTRICT ON UPDATE RESTRICT;