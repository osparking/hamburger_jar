ALTER TABLE hamburger.burger ADD `user` int(11) NULL 
	COMMENT 'id of user who designed this burger';
ALTER TABLE hamburger.burger ADD CONSTRAINT burger_FK 
	FOREIGN KEY (`user`) REFERENCES hamburger.`user`(id) 
	ON DELETE CASCADE ON UPDATE CASCADE;