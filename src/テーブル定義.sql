CREATE TABLE `customer` (
  `id` int NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `item` (
  `id` INT PRIMARY KEY,     -- 商品ID
  `name` VARCHAR(50),       -- 商品名
  `price` INT               -- 価格
);

CREATE TABLE `history` (
  `purchase_id` INT NOT NULL AUTO_INCREMENT,  -- 購入履歴の一意なID
  `customer_id` INT,                         -- 顧客ID
  `item_id` INT,                             -- 商品ID
  `purchase_date` DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 購入日時
  `quantity` INT,                            -- 購入数
  PRIMARY KEY (`purchase_id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`),  -- customerテーブルとのリレーション
  FOREIGN KEY (`item_id`) REFERENCES `item`(`id`)           -- itemテーブルとのリレーション
);

-- customerテーブルにダミーデータを追加
INSERT INTO `customer` (`id`, `name`, `address`) VALUES
(1, 'name1', 'address1'),
(2, 'name2', 'address2');

-- itemテーブルにダミーデータを追加
INSERT INTO `item` (`id`, `name`, `price`) VALUES
(1, 'item1', 1000),
(2, 'item2', 2000),
(3, 'item3', 3000);

-- historyテーブルにダミーデータを追加（購入数を含む）
INSERT INTO `history` (`customer_id`, `item_id`, `purchase_date`, `quantity`) VALUES
(1, 1, '2024-12-19 10:00:00', 2),   -- 顧客1がitem1を2個購入
(1, 2, '2024-12-19 11:00:00', 1),   -- 顧客1がitem2を1個購入
(2, 3, '2024-12-19 12:00:00', 3);   -- 顧客2がitem3を3個購入

