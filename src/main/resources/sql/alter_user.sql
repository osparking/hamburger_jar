ALTER TABLE hamburger.`user` ADD `role` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL;
ALTER TABLE hamburger.`user` ADD enabled tinyint(1) DEFAULT 0 NULL;
ALTER TABLE hamburger.`user` ADD provider varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL NULL;
