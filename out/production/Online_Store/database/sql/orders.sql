INSERT INTO `online_store`.`orders`
(`order_date`, `payment`, `products`)
VALUES
('2023-05-28', 'Cash', 'Tumble Tower, Games, 17.78, 3, Monopoly, Games, 68.99, 5'),
('2023-05-18', 'Card', 'Tumble Tower, Games, 17.78, 9, Monopoly, Games, 68.99, 8, Leash, Pet, 5.77, 1'),
('2023-05-18', 'Card', 'Tumble Tower, Games, 17.78, 17, Monopoly, Games, 68.99, 10'),
('2023-05-21', 'Cash', 'Lipstick, Fashion, 25.68, 1, Monopoly, Games, 68.99, 5, Tumble Tower, Games, 17.78, 2');

INSERT INTO `online_store`.`orders`
(`order_date`, `payment`, `products`)
VALUES
('2023-05-19', 'Card', 'Tumble Tower, Games, 17.78, 9, Leash, Pet, 5.77, 1'),
('2023-05-19', 'Card', 'Tumble Tower, Games, 17.78, 17, Monopoly, Games, 68.99, 5, Lipstick, Fashion, 25.68, 1');

SELECT * FROM orders;