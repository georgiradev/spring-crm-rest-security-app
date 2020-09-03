CREATE DATABASE  IF NOT EXISTS `web_employee_tracker`;

USE `web_employee_tracker`;

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `employee` VALUES
	(1,'David','Adams','david@demo.com'),
	(2,'John','Doe','john@demo.com'),
	(3,'Ajay','Rao','ajay@demo.com'),
	(4,'Mary','Public','mary@demo.com'),
	(5,'Maxwell','Dixon','max@demo.com');
