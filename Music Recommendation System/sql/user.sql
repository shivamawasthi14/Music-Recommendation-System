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