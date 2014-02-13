CREATE TABLE IF NOT EXISTS `word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `explanation` varchar(16384) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;