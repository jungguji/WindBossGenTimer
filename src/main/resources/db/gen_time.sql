-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.gen_time
(
    `id`         INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `boss_name`  VARCHAR(80)    NULL        COMMENT '보스 이름',
    `gen_time`   TIME           NULL        COMMENT '젠 타임',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.gen_time COMMENT '보스 별 젠 타임';