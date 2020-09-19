-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.user
(
    `id`        INT             NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `email`     VARCHAR(500)    NOT NULL    COMMENT '메일',
    `password`  VARCHAR(200)    NOT NULL    COMMENT '비밀번호',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.user COMMENT '유저';


-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.channel
(
    `id`            INT     NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `main_channel`  INT     NOT NULL    COMMENT '주 채널',
    `sub_channel`   INT     NOT NULL    COMMENT '서브 채널',
    `kill_time`     TIME    NULL        COMMENT '보스 죽인 시간',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.channel COMMENT '채널';


-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.region
(
    `id`    INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `name`  VARCHAR(45)    NOT NULL    COMMENT '지역명',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.region COMMENT '지역';


-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.dungeon
(
    `id`          INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `region_id`   INT            NOT NULL    COMMENT '지역 아이디',
    `name`        VARCHAR(70)    NOT NULL    COMMENT '던전명',
    `channel_id`  INT            NULL        COMMENT '채널 아이디',
    `user_id`     INT            NULL        COMMENT '유저 아이디',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.dungeon COMMENT '던전';

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_region_id_region_id FOREIGN KEY (region_id)
        REFERENCES wind_boss_gen_timer.region (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_channel_id_channel_id FOREIGN KEY (channel_id)
        REFERENCES wind_boss_gen_timer.channel (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_user_id_user_id FOREIGN KEY (user_id)
        REFERENCES wind_boss_gen_timer.user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.boss
(
    `id`          INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `name`        VARCHAR(85)    NOT NULL    COMMENT '보스명',
    `gen_time`    TIME           NOT NULL    COMMENT '젠 타임',
    `dungeon_id`  INT            NOT NULL    COMMENT '던전 아이디',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.boss COMMENT '보스';

ALTER TABLE wind_boss_gen_timer.boss
    ADD CONSTRAINT FK_boss_dungeon_id_dungeon_id FOREIGN KEY (dungeon_id)
        REFERENCES wind_boss_gen_timer.dungeon (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


