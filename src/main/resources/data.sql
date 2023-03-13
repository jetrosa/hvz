INSERT INTO app_user (first_name, last_name)
VALUES
    ('Admin', 'Admin'),--1
    ('Joe', 'Jackson'),--2
    ('Alice', 'Smith'),--3
    ('Max', 'Parker'),--4
    ('Nada', 'No'),--5
    ('Li', 'Won'),--6
    ('Ali', 'Mohammed'),--7
    ('Jesus', 'Gutierrez'),--8
    ('Tom', 'Teamless'),--9
    ('Lana', 'Loner');--10


INSERT INTO game (game_name, description, game_state, start_date_time, latitude_nw, longitude_nw, latitude_se, longitude_se)
VALUES
    ('Test game 1', 'Let''s go!', 'REGISTRATION', timestamp '2023-03-10 00:51:14', 60.16040115628537, 24.86448652733676, 60.1583941685908, 24.86845283380156),
    ('Test game 2', 'Zombie fest', 'REGISTRATION', timestamp '2023-04-11 00:51:14', 62.16040115628537, 22.86448652733676, 62.1583941685908, 22.86845283380156);


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


INSERT INTO kill (latitude, longitude, time_of_death, game_id, killer_id, victim_id)
VALUES
    (60.15930173697559, 24.86613540516308, timestamp '2023-03-11 00:52:14',1,1,3),
    (60.160177249786294, 24.86634998188868, timestamp '2023-03-12 00:57:14',1,1,4),
    (60.158362136307616, 24.865706251711888, timestamp '2023-03-12 00:59:14',1,3,2),

    (60.158362136307616, 24.865706251711888, timestamp '2023-03-12 00:59:14',2,11,12);


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


INSERT INTO squad_checkin (start_date_time, end_date_time, latitude, longitude, game_id, squad_id)
VALUES
    (timestamp '2023-03-15 00:00:00', timestamp '2023-03-15 00:30:00', 60.15830173697559, 24.86513540516308, 1, 1),
    (timestamp '2023-03-15 00:00:00', timestamp '2023-03-15 00:30:00', 60.15930173697559, 24.86713540516308, 1, 2),
    (timestamp '2023-03-15 00:00:00', timestamp '2023-03-15 00:30:00', 60.16030173697559, 24.86813540516308, 1, 3),
    (timestamp '2023-03-15 00:00:00',timestamp '2023-03-15 00:30:00', 60.16130173697559, 24.86313540516308, 1, 4),

    (timestamp '2023-03-15 00:00:00',timestamp '2023-03-15 00:30:00', 60.16130173697559, 24.86313540516308, 2, 5);


INSERT INTO mission (mission_name, description, start_date_time, end_date_time, is_human_visible, is_zombie_visible, game_id)
VALUES
    ('human mission 1', 'food',timestamp '2023-03-15 01:00:00',timestamp '2023-03-15 02:00:00',true,false,1),
    ('human mission 2', 'weapons',timestamp '2023-03-15 01:01:00',timestamp '2023-03-15 02:01:00',true,false,1),
    ('zombie mission 1', 'brains',timestamp '2023-03-15 01:02:00',timestamp '2023-03-15 02:02:00',false,true,1),
    ('zombie mission 2', 'brains',timestamp '2023-03-15 01:03:00',timestamp '2023-03-15 02:03:00',false,true,1),
    ('hz mission 1','ctf',timestamp '2023-03-15 01:04:00',timestamp '2023-03-15 02:04:00',true,true,1),
    ('hz mission 2','ctf',timestamp '2023-03-15 01:05:00',timestamp '2023-03-15 02:05:00',true,true,1);


INSERT INTO chat_message (chat_time, is_human_global, is_zombie_global, message, game_id, player_id, squad_id)
VALUES
    (timestamp '2023-03-10 00:10:11',true,false,'human global chat message 1', 1,1,null),
    (timestamp '2023-03-10 00:10:12',true,false,'human global chat message 2', 1,1,null),
    (timestamp '2023-03-10 00:10:13',true,false,'human global chat message 3', 1,1,null),
    (timestamp '2023-03-10 00:10:14',false,true,'zombie global chat message 1', 1,5,null),
    (timestamp '2023-03-10 00:10:15',false,true,'zombie global chat message 2', 1,5,null),
    (timestamp '2023-03-10 00:10:16',false,true,'zombie global chat message 3', 1,6,null),
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

