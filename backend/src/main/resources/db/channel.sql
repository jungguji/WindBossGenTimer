CREATE TABLE wind_boss_gen_timer.channel
(
    `id`            INT     NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `main_channel`  INT     NOT NULL    COMMENT '주 채널',
    `sub_channel`   INT     NOT NULL    COMMENT '서브 채널',
    `kill_time`     TIME    NULL        COMMENT '보스 죽인 시간',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.channel COMMENT '채널';