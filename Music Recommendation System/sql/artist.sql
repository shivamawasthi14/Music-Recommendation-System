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

