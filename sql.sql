CREATE SCHEMA `airlineapp`;

CREATE TABLE `airlineapp`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(500) NOT NULL,
  `email` VARCHAR(500) NOT NULL,
  `username` VARCHAR(500) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `airlineapp`.`booking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bookingToken` VARCHAR(3000) NULL,
  `cityFrom` VARCHAR(500) NULL,
  `cityTo` VARCHAR(500) NULL,
  `bookingId` VARCHAR(1000) NULL,
  `arrival` VARCHAR(500) NULL,
  `departure` VARCHAR(500) NULL,
  `price` DOUBLE NULL,
  `userId` INT NULL,
  PRIMARY KEY (`id`));
