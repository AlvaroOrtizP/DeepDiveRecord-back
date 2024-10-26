USE DeepDiveRecord;


DROP TABLE IF EXISTS `configuration_data`;
DROP TABLE IF EXISTS `tide_table`;
DROP TABLE IF EXISTS `wind_conditions`;
DROP TABLE IF EXISTS `dive_day_and_fishing`;
DROP TABLE IF EXISTS `dive_day`;
DROP TABLE IF EXISTS `fishing`;
DROP TABLE IF EXISTS `fish`;


CREATE TABLE `configuration_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ID_WINDWURU` int NOT NULL,
  `ID_AEMET` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `configuration_data` VALUES (3,487006,'play_v2_3900602',1),(4,177206,'play_v2_1507101',1);

CREATE TABLE `dive_day` (
  `dive_day_id` int NOT NULL AUTO_INCREMENT,
  `day` int NOT NULL,
  `beginning` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `end` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `site` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `year` int NOT NULL,
  `month` int NOT NULL,
  `assessment` tinyint(1) NOT NULL,
  `id_geographic` int NOT NULL,
  `presence_of_jellyfish` tinyint(1) NOT NULL DEFAULT '0',
  `water_visibility` tinyint(1) NOT NULL DEFAULT '0',
  `sea_background` tinyint(1) NOT NULL,
  `fish_grass` tinyint(1) NOT NULL,
  `presence_plastic` tinyint(1) NOT NULL,
  PRIMARY KEY (`dive_day_id`),
  KEY `diveday_ibfk_2` (`id_geographic`),
  CONSTRAINT `diveday_ibfk_2` FOREIGN KEY (`id_geographic`) REFERENCES `geographical_location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `fish` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `site` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `first_sighting` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `first_last` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `start_season` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `end_season` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `first_life_warning` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `fish` VALUES (1,'Sargo Comun','Cantabria','01/01','31/12','01/06','01/06','27/05/2024'),(2,'Cabracho','Cantabria','22/04','31/09','01/05','31/09','22/04/2023'),(3,'Ballesta','Cantabria','27/07','27/07','NA','NA','27/07/2022'),(4,'Maragota','Cantabria','27/07','27/07','NA','NA','27/07/2022'),(5,'Muble','Cantabria','01/01','31/12','01/10','31/11','01/11/2022'),(6,'Pulpo','Cantabria','01/04','01/10','03/05','01/08','01/11/2022'),(7,'Pinto','Cantabria','22/04','09/07','NA','NA','22/04/2023'),(8,'Centollo','Cantabria','22/04','01/05','PR','PR','22/04/2023'),(9,'Sepia','Cantabria','15/03','09/06','PR','PR','29/04/2023'),(10,'Congrio','Cantabria','29/04','31/07','NA','NA','29/04/2023'),(11,'Denton','Cantabria','26/08','26/08','NA','NA','26/08/2023'),(12,'Lubina','Cantabria','11/06','11/10','NA','NA','11/10/2023'),(13,'Salmonete','Cantabria','11/06','11/10','NA','NA','11/10/2023'),(14,'Rubio','Cantabria','26/04','26/04','NA','NA','26/04/2024'),(15,'Perla','Cantabria','27/05','27/05','NA','NA','27/05/2024'),(16,'Sargo Picuda','Cantabria','18/05','11/07','NA','NA','18/05/2024'),(17,'Sargo Breado/Real','Cantabria','11/07','11/07','NA','NA','11/07/2024'),(18,'Breca','Cantabria','11/07','11/07','NA','NA','11/07/2024');



CREATE TABLE `fishing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fish_id` int NOT NULL,
  `notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `caught` tinyint(1) NOT NULL,
  `weight` decimal(6,3) DEFAULT NULL,
  `lat_g` decimal(6,2) NOT NULL,
  `long_g` decimal(6,2) NOT NULL,
  `id_geographic` int NOT NULL,
  `dive_day_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fish_id` (`fish_id`),
  KEY `fishing_ibfk_2` (`id_geographic`),
  KEY `fishing_ibfk_3` (`dive_day_id`),
  CONSTRAINT `fishing_ibfk_1` FOREIGN KEY (`fish_id`) REFERENCES `fish` (`id`),
  CONSTRAINT `fishing_ibfk_2` FOREIGN KEY (`id_geographic`) REFERENCES `geographical_location` (`id`),
  CONSTRAINT `fishing_ibfk_3` FOREIGN KEY (`dive_day_id`) REFERENCES `dive_day` (`dive_day_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `geographical_location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `site` varchar(40) NOT NULL,
  `id_windwuru` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `geographical_location` VALUES (1,'Acantilado_Isla_Oeste','Isla','487006'),(2,'Acantilado_Isla_Este','Isla','487006'),(3,'Quintres','Ajo','487006');


CREATE TABLE `tide_table` (
  `day` int NOT NULL,
  `month` int NOT NULL,
  `year` int NOT NULL,
  `site` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `moon_phase` int NOT NULL,
  `coefficient0H` int NOT NULL,
  `coefficient12H` int NOT NULL,
  `morning_high_tide_time` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `morning_high_tide_height` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `afternoon_high_tide_time` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `afternoon_high_tide_height` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `morning_low_tide_time` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `morning_low_tide_height` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `afternoon_low_tide_time` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `afternoon_low_tide_height` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`day`,`month`,`year`,`site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `wind_conditions` (
  `year` int NOT NULL,
  `month` int NOT NULL,
  `day` int NOT NULL,
  `site` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time_of_day` int NOT NULL DEFAULT '0',
  `wind` int DEFAULT NULL,
  `wind_direction` int DEFAULT NULL,
  `wind_direction_nm` varchar(4) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gusts_of_wind` double DEFAULT NULL,
  `wave_height` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `wave_period` int DEFAULT NULL,
  `wave_direction` int DEFAULT NULL,
  `wave_direction_nm` varchar(4) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `earth_temperature` int DEFAULT '0',
  `water_temperature` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `condition_code` int DEFAULT NULL,
  `condition_description` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`year`,`month`,`day`,`time_of_day`,`site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;