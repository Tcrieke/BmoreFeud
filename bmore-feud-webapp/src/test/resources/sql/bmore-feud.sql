

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(700) NOT NULL,
  `last_used` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
);

CREATE TABLE `value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `value` varchar(25) NOT NULL,
  `points` int(11),
  PRIMARY KEY (`id`),
  UNIQUE KEY (`category_id`,`value`),
  CONSTRAINT `value_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE `accepted` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value_id` int(11),
  `value` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `accepted_value_id` FOREIGN KEY (`value_id`) REFERENCES `value` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);