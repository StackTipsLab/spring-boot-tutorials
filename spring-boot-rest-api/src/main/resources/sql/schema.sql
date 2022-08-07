CREATE TABLE `products` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL DEFAULT '',
  `price` decimal(10,2) DEFAULT NULL,
  `description` text,
  `in_stock` tinyint(1) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `units_available` decimal(11,0) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;