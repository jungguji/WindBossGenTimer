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
CREATE TABLE wind_boss_gen_timer.dungeon
(
    `id`           INT     NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `user_id`       INT     NOT NULL    COMMENT '유저 id',
    `main_channel`  INT     NULL        COMMENT '주 채널',
    `sub_channel`   INT     NULL        COMMENT '서브 채널',
    `kill_time`     TIME    NULL        COMMENT '보스 죽인 시간',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.dungeon COMMENT '던전';

ALTER TABLE wind_boss_gen_timer.dungeon
    ADD CONSTRAINT FK_dungeon_user_id_user_id FOREIGN KEY (user_id)
        REFERENCES wind_boss_gen_timer.user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- user Table Create SQL
CREATE TABLE wind_boss_gen_timer.gen_time
(
    `id`          INT            NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `boss_name`   VARCHAR(80)    NULL        COMMENT '보스 이름',
    `gen_time`    TIME           NULL        COMMENT '젠 타임',
    `dungeon_id`  INT            NULL        COMMENT '던전 아이디',
    PRIMARY KEY (id)
);

ALTER TABLE wind_boss_gen_timer.gen_time COMMENT '보스 별 젠 타임';

ALTER TABLE wind_boss_gen_timer.gen_time
    ADD CONSTRAINT FK_gen_time_dungeon_id_dungeon_아이디 FOREIGN KEY (dungeon_id)
        REFERENCES wind_boss_gen_timer.dungeon (아이디) ON DELETE RESTRICT ON UPDATE RESTRICT;


