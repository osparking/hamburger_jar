create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

ALTER TABLE hamburger.burger_ingredient DROP FOREIGN KEY ingredient_ID_FK2;
ALTER TABLE hamburger.burger_ingredient ADD CONSTRAINT ingredient_ID_FK2 FOREIGN KEY (ingredient) REFERENCES hamburger.ingredient(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE hamburger.corder_burger DROP FOREIGN KEY corder_burger_FK;
ALTER TABLE hamburger.corder_burger ADD CONSTRAINT corder_burger_FK FOREIGN KEY (corder) REFERENCES hamburger.corder(id);
