-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.dungeon
(
    `id`            INT     NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `user_id`       INT     NOT NULL    COMMENT '유저 id',
    `gen_time_id`   INT     NOT NULL    COMMENT '젠 타임 id',
    `main_channel`  INT     NULL        COMMENT '주 채널',
    `sub_channel`   INT     NULL        COMMENT '서브 채널',
    `kill_time`     TIME    NULL        COMMENT '보스 죽인 시간',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.dungeon COMMENT '던전';

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_user_id_user_id FOREIGN KEY (user_id)
        REFERENCES wind_boss_gen_timer.user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_gen_time_id_gen_time_id FOREIGN KEY (gen_time_id)
        REFERENCES wind_boss_gen_timer.gen_time (id) ON DELETE RESTRICT ON UPDATE RESTRICT;