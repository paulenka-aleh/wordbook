CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) COLLATE utf8_unicode_ci NOT NULL UNIQUE,
  `password` varbinary(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;