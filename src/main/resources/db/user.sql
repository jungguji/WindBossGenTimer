-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.user
(
    `id`        INT             NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `email`     VARCHAR(500)    NOT NULL    COMMENT '이메일',
    `password`  VARCHAR(200)    NOT NULL    COMMENT '비밀번호',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.user COMMENT '유저';