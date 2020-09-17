CREATE TABLE wind_boss_gen_timer.channel
(
    `id`            INT     NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `user_id`       INT     NOT NULL    COMMENT '유저 id',
    `dungeon_id`    INT     NOT NULL    COMMENT '던전 id',
    `main_channel`  INT     NOT NULL    COMMENT '주 채널',
    `sub_channel`   INT     NOT NULL    COMMENT '서브 채널',
    `kill_time`     TIME    NULL        COMMENT '보스 죽인 시간',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.channel COMMENT '채널';

ALTER TABLE wind_boss_gen_timer.channel
    ADD CONSTRAINT FK_channel_user_id_user_id FOREIGN KEY (user_id)
        REFERENCES wind_boss_gen_timer.user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE wind_boss_gen_timer.channel
    ADD CONSTRAINT FK_channel_dungeon_id_dungeon_id FOREIGN KEY (dungeon_id)
        REFERENCES wind_boss_gen_timer.dungeon (id) ON DELETE RESTRICT ON UPDATE RESTRICT;