-- hamburger.ingredient definition

CREATE TABLE `ingredient` (
  `id` char(4) NOT NULL,
  `name` varchar(40) NOT NULL,
  `type` varchar(40) NOT NULL,
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ingredient_un` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE hamburger.burger_ingredient DROP FOREIGN KEY ingredient_ID_FK2;
ALTER TABLE hamburger.burger_ingredient ADD CONSTRAINT ingredient_ID_FK2 FOREIGN KEY (ingredient) REFERENCES hamburger.ingredient(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE hamburger.corder_burger DROP FOREIGN KEY corder_burger_FK;
ALTER TABLE hamburger.corder_burger ADD CONSTRAINT corder_burger_FK FOREIGN KEY (corder) REFERENCES hamburger.corder(id);
