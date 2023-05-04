-- hamburger.corder definition

CREATE TABLE `corder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `cust_name` varchar(40) DEFAULT NULL,
  `addr_road` varchar(100) DEFAULT NULL,
  `addr_detail` varchar(40) DEFAULT NULL,
  `addr_zip` char(10) DEFAULT NULL,
  `cc_number` varchar(40) DEFAULT NULL,
  `cc_expiration` char(5) DEFAULT NULL,
  `cc_cvv` char(3) DEFAULT NULL,
  `placed_at` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `corder_user_id_FK` (`user_id`),
  CONSTRAINT `corder_user_id_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;