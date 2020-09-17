-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- region Table Create SQL
CREATE TABLE wind_boss_gen_timer.region
(
    `id`    INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `name`  VARCHAR(45)    NULL        COMMENT '지역명',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.region COMMENT '지역';


-- region Table Create SQL
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


-- region Table Create SQL
CREATE TABLE wind_boss_gen_timer.user
(
    `id`        INT             NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `email`     VARCHAR(500)    NOT NULL    COMMENT '메일',
    `password`  VARCHAR(200)    NOT NULL    COMMENT '비밀번호',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.user COMMENT '유저';


-- region Table Create SQL
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


-- region Table Create SQL
CREATE TABLE wind_boss_gen_timer.gen_time
(
    `id`          INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `boss_name`   VARCHAR(80)    NOT NULL    COMMENT '보스 이름',
    `gen_time`    TIME           NULL        COMMENT '젠 타임',
    `channel_id`  INT            NOT NULL    COMMENT '채널 아아디',
    `dungeon_id`  INT            NOT NULL    COMMENT '던전 아이디',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.gen_time COMMENT '보스 별 젠 타임';

ALTER TABLE wind_boss_gen_timer.gen_time
    ADD CONSTRAINT FK_gen_time_dungeon_id_dungeon_id FOREIGN KEY (dungeon_id)
        REFERENCES wind_boss_gen_timer.dungeon (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE wind_boss_gen_timer.gen_time
    ADD CONSTRAINT FK_gen_time_channel_id_channel_id FOREIGN KEY (channel_id)
        REFERENCES wind_boss_gen_timer.channel (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


