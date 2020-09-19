CREATE TABLE wind_boss_gen_timer.kill_time
(
    `id`          INT     NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `channel_id`  INT     NOT NULL    COMMENT '채널 아이디',
    `boss_id`     INT     NOT NULL    COMMENT '보스 아이디',
    `kill_time`   TIME    NOT NULL    COMMENT '보스 죽인 시간',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.kill_time COMMENT '죽인 시간';

ALTER TABLE wind_boss_gen_timer.kill_time
    ADD CONSTRAINT FK_kill_time_boss_id_boss_id FOREIGN KEY (boss_id)
        REFERENCES wind_boss_gen_timer.boss (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE wind_boss_gen_timer.kill_time
    ADD CONSTRAINT FK_kill_time_channel_id_channel_id FOREIGN KEY (channel_id)
        REFERENCES wind_boss_gen_timer.channel (id) ON DELETE RESTRICT ON UPDATE RESTRICT;