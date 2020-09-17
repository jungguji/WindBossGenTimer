CREATE TABLE wind_boss_gen_timer.gen_time
(
    `id`          INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `boss_name`   VARCHAR(80)    NOT NULL    COMMENT '보스 이름',
    `gen_time`    TIME           NULL        COMMENT '젠 타임',
    `channel_id`  INT            NOT NULL    COMMENT '채널 아아디',
    `dungeon_id`  INT            NOT NULL    COMMENT '던전 아이디',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.gen_time COMMENT '보스 별 젠 타임';

ALTER TABLE wind_boss_gen_timer.gen_time
    ADD CONSTRAINT FK_gen_time_dungeon_id_dungeon_id FOREIGN KEY (dungeon_id)
        REFERENCES wind_boss_gen_timer.dungeon (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE wind_boss_gen_timer.gen_time
    ADD CONSTRAINT FK_gen_time_channel_id_channel_id FOREIGN KEY (channel_id)
        REFERENCES wind_boss_gen_timer.channel (id) ON DELETE RESTRICT ON UPDATE RESTRICT;