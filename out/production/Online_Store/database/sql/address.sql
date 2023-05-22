CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `price` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `online_store`.`address`
(`country`, `city`, `street`, `number`, `entrance`, `floor`)
VALUES ("Romania", "Bucuresti", "Carol", '103', null, null);

INSERT INTO `online_store`.`address`
(`country`, `city`, `street`, `number`, `entrance`, `floor`)
VALUES ("Romania", "Bucuresti", "Carol", '3F', '203D', '10');

INSERT INTO `online_store`.`address`
(`country`, `city`, `street`, `number`, `entrance`, `floor`)
VALUES ("Romania", "Bucuresti", "Carol", '78', null, null);

INSERT INTO `online_store`.`address`
(`country`, `city`, `street`, `number`, `entrance`, `floor`)
VALUES ("Romania", "Bucuresti", "Carol", '109', null, null);

INSERT INTO `online_store`.`address`
(`country`, `city`, `street`, `number`, `entrance`, `floor`)
VALUES ("Romania", "Bucuresti", "Carol", '45', '29', 'P');

SELECT * FROM address;
