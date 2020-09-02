-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.user
(
    `id`        INT             NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `email`     VARCHAR(500)    NOT NULL    COMMENT '이메일',
    `password`  VARCHAR(200)    NOT NULL    COMMENT '비밀번호',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.user COMMENT '유저';


-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.gen_time
(
    `id`     INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `보스 이름`  VARCHAR(80)    NULL        COMMENT 'boss_name',
    `젠 타임`   TIME           NULL        COMMENT 'gen_time',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.gen_time COMMENT '보스 별 젠 타임';


-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.dungeon
(
    `아이디`       INT     NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `유저 아이디`    INT     NOT NULL    COMMENT 'user_id',
    `젠타임 아이디`   INT     NOT NULL    COMMENT 'gen_time_id',
    `주 채널`      INT     NULL        COMMENT 'main_channel',
    `서브 채널`     INT     NULL        COMMENT 'sub_channel',
    `보스 죽인 시간`  TIME    NULL        COMMENT 'kill_time',
    PRIMARY KEY (아이디)
);

ALTER TABLE wind_boss_gen_timer.dungeon COMMENT '던전';

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_유저 아이디_user_id FOREIGN KEY (유저 아이디)
        REFERENCES wind_boss_gen_timer.user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_젠타임 아이디_gen_time_id FOREIGN KEY (젠타임 아이디)
        REFERENCES wind_boss_gen_timer.gen_time (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


