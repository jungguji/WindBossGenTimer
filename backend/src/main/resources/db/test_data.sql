## 유저
INSERT INTO wind_boss_gen_timer.user (email, password) VALUES ('test@gmail.com', 'qwe123');


## 지역
INSERT INTO wind_boss_gen_timer.region (name) VALUES ('국내성');
INSERT INTO wind_boss_gen_timer.region (name) VALUES ('부여성');
INSERT INTO wind_boss_gen_timer.region (name) VALUES ('12지신의 유적');
INSERT INTO wind_boss_gen_timer.region (name) VALUES ('산적소굴');

## 던전

INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (1, 1, '전갈굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (1, 1, '유령굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (1, 1, '흑령굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (1, 1, '인형굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (1, 1, '백륜동');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (1, 1, '비밀세작의 집');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (1, 1, '흉가');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (2, 1, '전갈굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (2, 1, '유령굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (2, 1, '흑령굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (2, 1, '인형굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (2, 1, '물망동');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (2, 1, '비밀세작의 집');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (2, 1, '흉가');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (3, 1, '쥐왕굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (3, 1, '뱀왕굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (3, 1, '돼지왕굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (3, 1, '소왕굴');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (3, 1, '천상계제이계');
INSERT INTO wind_boss_gen_timer.dungeon (region_id, user_id, name) VALUES (3, 1, '용의 꼬리');

## 보스

INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (19, '백호왕', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (1, '힘쎈 전갈', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (1, '현랑 전갈', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (1, '백현 가재', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (6, '힘쎈 밀정', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (6, '밀정두목', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (7, '힘쎈 노인', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (15, '쥐왕', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (16, '뱀왕', NOW());
INSERT INTO wind_boss_gen_timer.boss (dungeon_id, name, gen_time) VALUES (17, '돼지왕', NOW());

## 채널

INSERT INTO wind_boss_gen_timer.channel (dungeon_id, main_channel, sub_channel) VALUES (19, 1, 1);
INSERT INTO wind_boss_gen_timer.channel (dungeon_id, main_channel, sub_channel) VALUES (1, 1, 1);
INSERT INTO wind_boss_gen_timer.channel (dungeon_id, main_channel, sub_channel) VALUES (1, 1, 10);
INSERT INTO wind_boss_gen_timer.channel (dungeon_id, main_channel, sub_channel) VALUES (1, 2, 2);
INSERT INTO wind_boss_gen_timer.channel (dungeon_id, main_channel, sub_channel) VALUES (1, 1, 3);
INSERT INTO wind_boss_gen_timer.channel (dungeon_id, main_channel, sub_channel) VALUES (6, 20, 2);
INSERT INTO wind_boss_gen_timer.channel (dungeon_id, main_channel, sub_channel) VALUES (6, 6, 7);

## 죽인 시간

INSERT INTO wind_boss_gen_timer.kill_time (channel_id, boss_id, kill_time) VALUES (1, 1, NOW());
INSERT INTO wind_boss_gen_timer.kill_time (channel_id, boss_id, kill_time) VALUES (2, 2, NOW());
INSERT INTO wind_boss_gen_timer.kill_time (channel_id, boss_id, kill_time) VALUES (2, 3, NOW());
INSERT INTO wind_boss_gen_timer.kill_time (channel_id, boss_id, kill_time) VALUES (2, 4, NOW());
INSERT INTO wind_boss_gen_timer.kill_time (channel_id, boss_id, kill_time) VALUES (6, 6, NOW());