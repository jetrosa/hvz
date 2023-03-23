INSERT INTO app_user (first_name, last_name, uuid)
VALUES
    ('Admin', 'Admin', 'fdh9'),--1
    ('Joe', 'Jackson', 'fds9'),--2
    ('Alice', 'Smith', 'fd29'),--3
    ('Max', 'Parker', 'fd89'),--4
    ('Nada', 'No', 'fd93'),--5
    ('Li', 'Won', 'fd49'),--6
    ('Ali', 'Mohammed', 'fs99'),--7
    ('Jesus', 'Gutierrez', 'fh99'),--8
    ('Tom', 'Teamless', 'fa99'),--9
    ('Lana', 'Loner', 'fdf9'),--10
    ('Lobby', 'Lob', 'ffff');--11


INSERT INTO game (game_name, description, game_state, start_date_time, end_date_time)
VALUES
    ('Test game 1', 'Let''s go!', 'REGISTRATION', timestamp '2023-03-10 00:51:14', timestamp '2023-04-10 00:51:14'),
    ('Test game 2', 'Zombie fest', 'REGISTRATION', timestamp '2023-04-11 00:51:14', timestamp '2023-04-10 00:51:14');

INSERT INTO map_coordinate (latitude, longitude, game_id)
VALUES
    (24.9706578, 60.1848488,1),
    (24.9660230, 60.1825442,1),
    (24.9649715, 60.1811785,1),
    (24.9654865, 60.1802929,1),
    (24.9666882, 60.1797913,1),
    (24.9739623, 60.1838245,1),
    (24.9706578, 60.1848488,1),

    (24.9441522, 60.1762272, 2),
    (24.9443936, 60.17443430, 2),
    (24.9500585, 60.1745997, 2),
    (24.9499834, 60.1754588, 2),
    (24.9441522, 60.1762272, 2);


INSERT INTO player (is_human, is_patient_zero, bite_code, app_user_id,  game_id)
VALUES
    (true, true, 'hw4a', 1,1),
    (true, false, 'hw4b', 2,1),
    (true, false, 'hw4c', 3,1),
    (true, false, 'hw4d', 4,1),
    (false, false, 'hw4e', 5,1),
    (false, false, 'hw4f', 6,1),
    (false, false, 'hw4g', 7,1),
    (false, false, 'hw4h', 8,1),
    (true, false, 'hw4i', 9,1),
    (true, false, 'hw4j', 10,1),

    (true, true, 'hw5a', 1,2),
    (true, false, 'hw5b', 2,2);


INSERT INTO bite (latitude, longitude, time_of_death, game_id, biter_id, victim_id)
VALUES
    (24.9671173, 60.1819467, timestamp '2023-03-11 00:52:14',1,1,3),
    (24.9683189, 60.1814986, timestamp '2023-03-12 00:57:14',1,1,4),
    (24.9699497, 60.1835151, timestamp '2023-03-12 00:59:14',1,3,2),

    (24.9463248, 60.1755762, timestamp '2023-03-12 00:59:14',2,11,12);


INSERT INTO squad (is_human, squad_name, game_id)
VALUES
    (true,'human squad 1',1),
    (true,'human squad 2',1),
    (false,'zombie squad 1',1),
    (false,'zombie squad 2',1),

    (false,'zombie squad game 2',2);


INSERT INTO squad_member (rank, player_id, squad_id)
VALUES
    (0,1,1),
    (0,2,1),
    (0,3,2),
    (0,4,2),
    (0,5,3),
    (0,6,3),
    (0,7,4),
    (0,8,4),

    (0,11,5);


INSERT INTO squad_checkin (start_date_time, end_date_time, latitude, longitude, game_id, squad_id, squad_member_id)
VALUES
    (timestamp '2023-03-15 00:00:00', timestamp '2023-04-15 00:30:00', 24.9731040, 60.1838565, 1, 1, 1),
    (timestamp '2023-03-15 00:00:00', timestamp '2023-04-15 00:30:00', 24.9704862, 60.1822561, 1, 2, 3);


INSERT INTO mission (mission_name, description, start_date_time, end_date_time, is_human_visible, is_zombie_visible, latitude, longitude, game_id)
VALUES
    ('human mission 1', 'food',timestamp '2023-03-15 01:00:00',timestamp '2023-03-15 02:00:00',true,false, 24.9670315, 60.1821494, 1),
    ('human mission 2', 'weapons',timestamp '2023-03-15 01:01:00',timestamp '2023-03-15 02:01:00',true,false, 24.9689198, 60.1830457, 1),
    ('zombie mission 1', 'brains',timestamp '2023-03-15 01:02:00',timestamp '2023-03-15 02:02:00',false,true,24.9669886, 60.1809971, 1),
    ('zombie mission 2', 'brains',timestamp '2023-03-15 01:03:00',timestamp '2023-03-15 02:03:00',false,true,24.9697781, 60.1843260, 1),
    ('hz mission 1','ctf',timestamp '2023-03-15 01:04:00',timestamp '2023-03-15 02:04:00',true,true,null,null,1),
    ('hz mission 2','ctf',timestamp '2023-03-15 01:05:00',timestamp '2023-03-15 02:05:00',true,true,null,null,1);


INSERT INTO chat_message (chat_time, is_human_global, is_zombie_global, message, game_id, player_id, squad_id)
VALUES
    (timestamp '2023-03-10 00:8:11',true,true,'global chat message 1', 1,1,null),
    (timestamp '2023-03-10 00:9:11',true,true,'global chat message 2', 1,1,null),
    (timestamp '2023-03-10 00:10:11',true,false,'human faction chat message 1', 1,1,null),
    (timestamp '2023-03-10 00:10:12',true,false,'human faction chat message 2', 1,1,null),
    (timestamp '2023-03-10 00:10:13',true,false,'human faction chat message 3', 1,1,null),
    (timestamp '2023-03-10 00:10:14',false,true,'zombie faction chat message 1', 1,5,null),
    (timestamp '2023-03-10 00:10:15',false,true,'zombie faction chat message 2', 1,5,null),
    (timestamp '2023-03-10 00:10:16',false,true,'zombie faction chat message 3', 1,6,null),
    (timestamp '2023-03-10 00:10:17',false,false,'human squad 1 chat message 1', 1,1,1),
    (timestamp '2023-03-10 00:10:18',false,false,'human squad 1 chat message 2', 1,1,1),
    (timestamp '2023-03-10 00:10:19',false,false,'human squad 1 chat message 3', 1,2,1),
    (timestamp '2023-03-10 00:10:20',false,false,'human squad 1 chat message 4', 1,2,1),
    (timestamp '2023-03-10 00:10:21',false,false,'human squad 2 chat message 1', 1,3,2),
    (timestamp '2023-03-10 00:10:22',false,false,'human squad 2 chat message 2', 1,3,2),
    (timestamp '2023-03-10 00:10:23',false,false,'human squad 2 chat message 4', 1,4,2),
    (timestamp '2023-03-10 00:10:24',false,false,'human squad 2 chat message 3', 1,4,2),
    (timestamp '2023-03-10 00:10:25',false,false,'zombie squad 1 chat message 1', 1,5,3),
    (timestamp '2023-03-10 00:10:26',false,false,'zombie squad 1 chat message 2', 1,5,3),
    (timestamp '2023-03-10 00:10:27',false,false,'zombie squad 1 chat message 3', 1,6,3),
    (timestamp '2023-03-10 00:10:28',false,false,'zombie squad 1 chat message 4', 1,6,3),
    (timestamp '2023-03-10 00:10:29',false,false,'zombie squad 2 chat message 1', 1,7,4),
    (timestamp '2023-03-10 00:10:30',false,false,'zombie squad 2 chat message 2', 1,7,4),
    (timestamp '2023-03-10 00:10:31',false,false,'zombie squad 2 chat message 3', 1,8,4),
    (timestamp '2023-03-10 00:10:32',false,false,'zombie squad 2 chat message 4', 1,8,4),

    (timestamp '2023-03-10 00:10:32',true,true,'global chat message game 2', 2,11,5);

