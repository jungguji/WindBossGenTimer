CREATE TABLE wind_boss_gen_timer.dungeon
(
    `id`         INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `region_id`  INT            NOT NULL    COMMENT '지역 아이디',
    `name`       VARCHAR(70)    NOT NULL    COMMENT '던전명',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.dungeon COMMENT '던전';

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_region_id_region_id FOREIGN KEY (region_id)
        REFERENCES wind_boss_gen_timer.region (id) ON DELETE RESTRICT ON UPDATE RESTRICT;