
CREATE TABLE `diveday` (
  `dive_day_id` int(11) NOT NULL,
  `day` varchar(2) NOT NULL,
  `beginning` varchar(2) NOT NULL,
  `end` varchar(2) NOT NULL,
  `site` varchar(50) NOT NULL,
  `notas` varchar(255) NOT NULL,
  `year` varchar(4) NOT NULL,
  `month` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `fishing` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `apuntes` varchar(255) NOT NULL,
  `pescado` tinyint(1) NOT NULL,
  `dive_day_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tide_table` (
  `day` varchar(4) NOT NULL,
  `month` varchar(2) NOT NULL,
  `year` varchar(2) NOT NULL,
  `moon_phase` int(4) NOT NULL,
  `morning_high_tide_time` varchar(5) NOT NULL,
  `morning_high_tide_height` decimal(2,2) NOT NULL,
  `afternoon_high_tide_time` varchar(5) NOT NULL,
  `afternoon_high_tide_height` decimal(2,2) NOT NULL,
  `morning_low_tide_time` varchar(5) NOT NULL,
  `morning_low_tide_height` decimal(2,2) NOT NULL,
  `afternoon_low_tide_time` varchar(5) NOT NULL,
  `afternoon_low_tide_height` decimal(2,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `windconditions` (
  `year` varchar(4) NOT NULL,
  `month` varchar(2) NOT NULL,
  `day` varchar(2) NOT NULL,
  `site` varchar(50) NOT NULL,
  `time_of_day` varchar(30) NOT NULL,
  `wind` int(11) DEFAULT NULL,
  `wind_direction` decimal(5,2) DEFAULT NULL,
  `gusts_of_wind` double DEFAULT NULL,
  `wave_height` varchar(5) NOT NULL,
  `wave_period` int(11) DEFAULT NULL,
  `earth_temperature` int(11) DEFAULT NULL,
  `water_temperature` varchar(2) NOT NULL,
  `condition_code` int(3) NOT NULL,
  `condition_description` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



ALTER TABLE `diveday`
  ADD PRIMARY KEY (`dive_day_id`);

ALTER TABLE `fishing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_fishing_diveday` (`dive_day_id`);

ALTER TABLE `tide_table`
  ADD PRIMARY KEY (`day`,`month`,`year`);

ALTER TABLE `windconditions`
  ADD PRIMARY KEY (`year`,`month`,`day`,`time_of_day`,`site`);

ALTER TABLE `diveday`
  MODIFY `dive_day_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `fishing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Filtros para la tabla `fishing`
--
ALTER TABLE `fishing`
  ADD CONSTRAINT `fk_fishing_diveday` FOREIGN KEY (`dive_day_id`) REFERENCES `diveday` (`dive_day_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
