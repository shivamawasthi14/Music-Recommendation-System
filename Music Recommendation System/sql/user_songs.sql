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
