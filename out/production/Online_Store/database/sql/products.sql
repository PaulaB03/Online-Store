CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `price` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `online_store`.`products`
(`name`, `category`, `price`)
VALUES    
('Tumble Tower', 'Games', 17.78),
('Poker', 'Games', 23.32),
('Monopoly', 'Games', 68.99),
('Mascara', 'Fashion', 38.99),
('Lipstick', 'Fashion', 25.68),
('Foundation', 'Fashion', 67.45),
('Food Bowl', 'Pet', 10.68),
('Catnip', 'Pet', 15.89),
('Leash', 'Pet', 5.77);

SELECT * FROM products;