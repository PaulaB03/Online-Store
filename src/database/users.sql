INSERT INTO `online_store`.`users`
(`name`, `phone`, `email`, `password`, `country`, `city`, `street`, `number`, `entrance`, `floor`)
VALUES
('Andra', '0788488579', 'andra@gmail.com', 'Password55', 'Romania', 'Bucuresti', 'Carol', '152', 'B', '10'),
('Matei', '0788488573', 'matei@gmail.com', 'Password55', 'Romania', 'Bucuresti', 'Carol', '5F', null, null),
('Andrei', '0788488578', 'andrei@gmail.com', 'Password55', 'Romania', 'Bucuresti', 'Carol', '3F', null, null);

SELECT * FROM users;
SELECT * FROM products;
SELECT * FROM orders;