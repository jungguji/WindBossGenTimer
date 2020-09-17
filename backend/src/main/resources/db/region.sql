CREATE TABLE wind_boss_gen_timer.region
(
    `id`    INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `name`  VARCHAR(45)    NULL        COMMENT '지역명',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.region COMMENT '지역';