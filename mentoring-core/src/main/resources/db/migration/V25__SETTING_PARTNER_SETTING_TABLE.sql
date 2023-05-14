DROP TABLE IF EXISTS `PARTNER_SETTINGS`;

DROP TABLE IF EXISTS `SETTINGS`;

CREATE TABLE `SETTINGS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  `LIFE_CYCLE_STATUS` varchar(100) NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(50) DEFAULT NULL,
  `UUID` varchar(50) NOT NULL,
  `DESIGNATION` varchar(50) NOT NULL,
  `SETTING_VALUE` INT NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_SETTING_DESIGNATION` (`DESIGNATION`,`SETTING_VALUE`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

CREATE TABLE `PARTNER_SETTINGS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  `LIFE_CYCLE_STATUS` varchar(100) NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(50) DEFAULT NULL,
  `UUID` varchar(50) NOT NULL,
  `SETTING_ID` bigint(20) NOT NULL,
  `PARTNER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_SETTING_PARTNER_KEY` (`PARTNER_ID`,`SETTING_ID`),
  CONSTRAINT `FK_PARTNER_KEY` FOREIGN KEY (`PARTNER_ID`) REFERENCES `PARTNERS` (`ID`),
  CONSTRAINT `FK_SETTING_KEY` FOREIGN KEY (`SETTING_ID`) REFERENCES `SETTINGS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;