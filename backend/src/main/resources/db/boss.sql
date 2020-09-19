CREATE TABLE wind_boss_gen_timer.boss
(
    `id`          INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `dungeon_id`  INT            NOT NULL    COMMENT '던전 아이디',
    `name`        VARCHAR(85)    NOT NULL    COMMENT '보스명',
    `gen_time`    TIME           NOT NULL    COMMENT '젠 타임',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.boss COMMENT '보스';

ALTER TABLE wind_boss_gen_timer.boss
    ADD CONSTRAINT FK_boss_dungeon_id_dungeon_id FOREIGN KEY (dungeon_id)
        REFERENCES wind_boss_gen_timer.dungeon (id) ON DELETE RESTRICT ON UPDATE RESTRICT;