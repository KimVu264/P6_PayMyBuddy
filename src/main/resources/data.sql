-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema paymybuddy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema paymybuddy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paymybuddy` DEFAULT CHARACTER SET utf8 ;
USE `paymybuddy` ;

-- -----------------------------------------------------
-- Table `paymybuddy`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `tel` INT NOT NULL,
  `birthday` DATE NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `balance` DOUBLE NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`account_id`, `user_id`),
  INDEX `fk_account_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy`.`transfer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`transfer` (
  `transfer_id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `motif` VARCHAR(255) NULL,
  `date` DATE NOT NULL,
  `account_sender_id` INT NOT NULL,
  `account_receipt_id` INT NOT NULL,
  PRIMARY KEY (`transfer_id`, `account_sender_id`, `account_receipt_id`),
  INDEX `fk_transfer_account1_idx` (`account_sender_id` ASC, `account_receipt_id` ASC) VISIBLE,
  CONSTRAINT `fk_transfer_account1`
    FOREIGN KEY (`account_sender_id` , `account_receipt_id`)
    REFERENCES `paymybuddy`.`account` (`account_id` , `user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy`.`user_contacts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`user_contacts` (
  `user_id` INT NOT NULL,
  `contact_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `contact_id`),
  INDEX `fk_table1_users2_idx` (`contact_id` ASC) VISIBLE,
  CONSTRAINT `fk_table1_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_users2`
    FOREIGN KEY (`contact_id`)
    REFERENCES `paymybuddy`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
