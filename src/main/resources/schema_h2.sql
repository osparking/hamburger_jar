CREATE TABLE PUBLIC.BURGER
(
   ID BIGINT NOT NULL AUTO_INCREMENT,
   NAME CHARACTER VARYING (40) DEFAULT NULL,
   CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   CONSTRAINT CONSTRAINT_7 PRIMARY KEY (ID)
);
CREATE TABLE PUBLIC.CORDER
(
   ID BIGINT NOT NULL AUTO_INCREMENT,
   CUST_NAME CHARACTER VARYING (40) DEFAULT NULL,
   ADDR_ROAD CHARACTER VARYING (100) DEFAULT NULL,
   ADDR_DETAIL CHARACTER VARYING (40) DEFAULT NULL,
   ADDR_ZIP CHARACTER (10) DEFAULT NULL,
   CC_NUMBER CHARACTER VARYING (40) DEFAULT NULL,
   CC_EXPIRATION CHARACTER (5) DEFAULT NULL,
   CC_CVV CHARACTER (3) DEFAULT NULL,
   PLACED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   CONSTRAINT CONSTRAINT_76 PRIMARY KEY (ID)
);
CREATE TABLE PUBLIC.INGREDIENT
(
   ID CHARACTER (4) NOT NULL,
   NAME CHARACTER VARYING (40) NOT NULL,
   "TYPE" CHARACTER VARYING (40) NOT NULL,
   CONSTRAINT CONSTRAINT_1 PRIMARY KEY (ID)
);
CREATE TABLE PUBLIC."USER"
(
   ID BIGINT NOT NULL AUTO_INCREMENT,
   USERNAME CHARACTER VARYING (40) DEFAULT NULL,
   PASSWORD CHARACTER VARYING (250) DEFAULT NULL,
   FULLNAME CHARACTER VARYING (40) DEFAULT NULL,
   ADDR_ROAD CHARACTER VARYING (100) DEFAULT NULL,
   ADDR_DETAIL CHARACTER VARYING (50) DEFAULT NULL,
   ADDR_ZIP CHARACTER VARYING (10) DEFAULT NULL,
   PHONE_NUMBER CHARACTER VARYING (20) DEFAULT NULL,
   "ROLE" CHARACTER VARYING (45) DEFAULT 'ROLE_USER' NOT NULL,
   ENABLED TINYINT DEFAULT 0,
   PROVIDER CHARACTER VARYING (15) DEFAULT NULL,
   CONSTRAINT CONSTRAINT_2 PRIMARY KEY (ID),
   CONSTRAINT USER_UN UNIQUE (USERNAME)
);
CREATE TABLE PUBLIC.BURGER_INGREDIENT
(
   ID BIGINT NOT NULL AUTO_INCREMENT,
   BURGER BIGINT,
   BURGER_KEY SMALLINT,
   INGREDIENT CHARACTER VARYING (5),
   CONSTRAINT BURGER_INGREDIENT_PK PRIMARY KEY (ID)
);
CREATE INDEX BURGER_INGREDIENT_FK_INDEX_2 ON PUBLIC.BURGER_INGREDIENT (BURGER);
CREATE INDEX INGREDIENT_FK_INDEX_2 ON PUBLIC.BURGER_INGREDIENT (INGREDIENT);
CREATE UNIQUE INDEX PRIMARY_KEY_F ON PUBLIC.BURGER_INGREDIENT (ID);
-- PUBLIC.BURGER_INGREDIENT foreign keys
ALTER TABLE PUBLIC.BURGER_INGREDIENT ADD CONSTRAINT BURGER_INGREDIENT_FK FOREIGN KEY (BURGER) REFERENCES PUBLIC.BURGER (ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE PUBLIC.BURGER_INGREDIENT ADD CONSTRAINT INGREDIENT_FK FOREIGN KEY (INGREDIENT) REFERENCES PUBLIC.INGREDIENT (ID) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE TABLE PUBLIC.CORDER_BURGER
(
   ID BIGINT NOT NULL AUTO_INCREMENT,
   CORDER BIGINT DEFAULT NULL,
   CORDER_KEY SMALLINT DEFAULT NULL,
   BURGER BIGINT DEFAULT NULL,
   CONSTRAINT CONSTRAINT_B PRIMARY KEY (ID)
);
CREATE INDEX BURGER_FK_INDEX_B ON PUBLIC.CORDER_BURGER (BURGER);
CREATE INDEX CORDER_BURGER_FK_INDEX_B ON PUBLIC.CORDER_BURGER (CORDER);
-- PUBLIC.CORDER_BURGER foreign keys
ALTER TABLE PUBLIC.CORDER_BURGER ADD CONSTRAINT BURGER_FK FOREIGN KEY (BURGER) REFERENCES PUBLIC.BURGER (ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE PUBLIC.CORDER_BURGER ADD CONSTRAINT CORDER_BURGER_FK FOREIGN KEY (CORDER) REFERENCES PUBLIC.CORDER (ID) ON DELETE CASCADE ON UPDATE CASCADE;