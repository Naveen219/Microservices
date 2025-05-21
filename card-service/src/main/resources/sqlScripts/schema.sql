CREATE database IF NOT EXISTS microservices
USE microservices;
CREATE TABLE IF NOT EXISTS `cards` (
        `card_id` int auto_increment,
        `mobile_number` varchar(15) not null,
        `card_number` varchar(100) not null,
        `card_type` varchar(100) not null,
        `total_limit` int not null,
        `amount_used` int  not null,
        `available_amount` int not null,
        `created_at` date not null,
        `created_by` varchar(20) not null,
        `updated_at` date default null,
        `updated_by` varchar(20) not null,
         PRIMARY KEY (`card_id`)

);
