create database music_rs;
use music_rs;

CREATE TABLE `artist` (
  `artist_id` int(11) NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(80) DEFAULT NULL,
  `genre` varchar(80) DEFAULT NULL,
  `gender` varchar(80) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  PRIMARY KEY (`artist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `music_rs`.`artist`
(`artist_name`,
`genre`,
`gender`,
`age`,
`likes`)
VALUES
('Taylor Swift', 'Pop', 'Female', 26, 2000),
('Katy Perry', 'Pop', 'Female', 30, 1800),
('Lana Del Rey', 'Alternative', 'Female', 28, 1400),
('Linkin Park', 'Rock', 'Male', 35, 1720),
('Snow Patrol', 'Rock', 'Male', 48, 1100),
('Selena Gomez', 'Dance-Pop', 'Female', 26, 1200),
('Adele', 'Slow-Pop', 'Female', 26, 1900),
('Eminem', 'Rap', 'Male', 40, 1700),
('Amy Winehouse', 'Jazz', 'Female', 27, 900),
('Justin Bieber', 'Pop', 'Male', 22, 1800),
('Miley Cyrus', 'Dance-Pop', 'Female', 26, 1200),
('ColdPlay', 'Alternative', 'Male', 34, 2110);

CREATE TABLE `song` (
  `song_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) DEFAULT NULL,
  `artist_name` varchar(80) DEFAULT NULL,
  `album` varchar(80) DEFAULT NULL,
  `genre` varchar(80) DEFAULT NULL,
  `danceability` int(11) DEFAULT NULL,
  `lyrics` double DEFAULT NULL,
  `energy` double DEFAULT NULL,
  `tempo` double DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  PRIMARY KEY (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `music_rs`.`song`
(`title`,`artist_name`,`album`,`genre`,
`danceability`,
`lyrics`,
`energy`,
`tempo`,
`likes`)
VALUES
('Blank Space', 'Taylor Swift', '1989', 'Pop', 7.5, 7, 7.6, 6, 2000),
('Shake it off', 'Taylor Swift', '1989', 'Pop', 7.5, 9.8, 8.1, 7.3, 1890),
('Bad Blood', 'Taylor Swift', '1989', 'Pop', 6.5, 9.8, 7.1, 8.3, 1989),
('Roar', 'Katy Perry', 'Prism', 'Pop', 8, 5, 6.2, 6.8, 800),
('Dark Horse', 'Katy Perry', 'Prism', 'Pop', 5, 8.5, 6.6, 6.3, 200),
('California Gurls', 'Katy Perry', 'Prism', 'Pop', 4, 9.5, 8.6, 6.3, 1950),
('Firework', 'Katy Perry', 'Prism', 'Pop', 7, 9.5, 9.8, 9.1, 1400),
('Summertime Sadness', 'Lana Del Rey', 'Born To Die', 'Pop', 6, 7.3, 5.6, 6.3, 300),
('Bel Air', 'Lana Del Rey', 'Born To Die', 'Alternative', 5, 8.5, 6.6, 6.3, 200),
('West Coast', 'Lana Del Rey', 'Ultraviolence', 'Alternative', 8, 8.5, 4.6, 6.3, 1000),
('Music to watch boys to', 'Lana Del Rey', 'Honeymoon', 'Alternative', 5, 4.5, 7.6, 8.3, 400),
('Come & Get it', 'Selena Gomez', 'Stars Dance', 'Pop', 4, 8.5, 1.6, 3.3, 2100),
('Same old love', 'Selena Gomez', 'Revival', 'Pop', 6, 6.5, 4.6, 7.3, 165),
('Hands to myself', 'Selena Gomez', 'Revival', 'Pop', 7, 7.5, 4.8, 9.1, 5800),
('Hello', 'Adele', '25', 'Slow-Pop', 8, 8.5, 2.6, 6.3, 6000),
('Rumour has it', 'Adele', '21', 'Slow-Pop', 8, 9.5, 4.6, 6.3, 1650),
('Rolling in the deep', 'Adele', '21', 'Slow-Pop', 8, 8.5, 4.8, 9.1, 8000),
('Numb', 'Linkin Park', 'Meteora', 'Rock', 7, 8.2, 8.6, 8, 3500),
('Faint', 'Linkin Park', 'Meteora', 'Rock', 10, 8, 10, 10, 6500),
('Burn it down', 'Linkin Park', 'Living Things', 'Rock', 10, 9, 9, 9, 7500),
('Run', 'Snow Patrol', 'Final Straw', 'Rock', 7, 9, 4.6, 4, 4600),
('Chasing Cars', 'Snow Patrol', 'Eyes Open', 'Rock', 6, 10, 6, 7, 4800),
('Open your Eyes', 'Snow Patrol', 'Eyes Open', 'Rock', 5, 7, 5, 6, 4310),
('Not Afraid','Eminem','Recovery', 'Rap',1 ,8 ,8 ,9 ,5800),
('Love the Way you lie','Eminem','Recovery', 'Rap',1 ,8 ,8 ,9 ,5500),
('Space Bound','Eminem','Recovery', 'Rap', 2 ,6 ,8 ,9 ,3300),
('Rehab', 'Amy Winehouse', 'Back to Black', 'Jazz', 8.5, 10, 8.6, 6.3, 2110),
('Addicted', 'Amy Winehouse', 'Back to Black', 'Jazz', 9.5, 10, 7.6, 7.3, 4165),
('Body and Soul', 'Amy Winehouse', 'Back to Black', 'Jazz', 9.5, 10, 6.8, 9.1, 5900),
('Sorry','Justin Bieber','Purpose', 'Pop',8,7,8,9,7800),
('What do you mean','Justin Bieber','Purpose', 'Pop', 7,7,8,7,6000),
('Baby','Justin Bieber','My World 1.0', 'Pop', 7,6,6,7,8000),
('Wrecking Ball','Miley Cyrus','Bangerz','Dance-Pop',6,9,6,7,6555),
('We cant stop','Miley Cyrus','Bangerz','Dance-Pop', 9,6,6,8,5555),
('Stay','Miley Cyrus','Cant be tamed','Dance-Pop', 6,6,6,6,2000),
('Viva La Vida','ColdPlay','Viva La Vida','Alternative',6,9,8,8,5666),
('Paradise','ColdPlay','Mylo Xyloto', 'Alternative',6,9,9,10,6000),
('Every Teardrop is a Waterfall','ColdPlay','Mylo Xyloto', 'Alternative',4,9,6,8,2900);

CREATE TABLE `song` (
  `song_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) DEFAULT NULL,
  `artist_name` varchar(80) DEFAULT NULL,
  `album` varchar(80) DEFAULT NULL,
  `genre` varchar(80) DEFAULT NULL,
  `danceability` int(11) DEFAULT NULL,
  `lyrics` double DEFAULT NULL,
  `energy` double DEFAULT NULL,
  `tempo` double DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  PRIMARY KEY (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `music_rs`.`song`
(`title`,
`artist_name`,
`album`,
`genre`,
`danceability`,
`lyrics`,
`energy`,
`tempo`,
`likes`)
VALUES
('Blank Space', 'Taylor Swift', '1989', 'Pop', 7.5, 7, 7.6, 6, 1000),
('Roar', 'Katy Perry', 'Prism', 'Pop', 8, 5, 6.2, 6.8, 800),
('Summertime Sadness', 'Lana Del Rey', 'Born To Die', 'Pop', 6, 7.3, 5.6, 6.3, 300),
('Numb', 'Linkin Park', 'Meteora', 'Rock', 9, 8.2, 8.6, 8, 500),
('Run', 'Snow Patrol', 'Final Straw', 'Pop', 7, 9, 4.6, 4, 600);

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(80) DEFAULT NULL,
  `user_name` varchar(80) NOT NULL UNIQUE,
  `is_preferences_set` bool DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `music_rs`.`user`
(`password`,
`user_name`,
`is_preferences_set`)
VALUES
('iiita','aepari14',0),
('iiita','shivam14',0),
('iiita','taraprasad',0),
('iiita','varun',0);

CREATE TABLE `user_songs` (
  `user_id` int(11) NOT NULL,
  `song_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `music_rs`.`user_songs`
(`user_id`,
`song_id`)
VALUES
(1, 2),
(1, 1),
(1, 3),
(1, 5),
(2, 5),
(2, 4),
(4, 3),
(4, 1),
(4, 2),
(3, 5);

CREATE TABLE `user_artists` (
  `user_id` int(11) NOT NULL,
  `artist_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `artist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `music_rs`.`user_artists`
(`user_id`,
`artist_id`)
VALUES
(1, 2),
(1, 1),
(1, 3),
(1, 5),
(2, 5),
(2, 4),
(4, 3),
(4, 1),
(4, 2),
(3, 5);
