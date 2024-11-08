-- Datos para geographical_location
INSERT INTO geographical_location (id, name, site, id_windwuru)
VALUES (1, 'Playa del Carmen', 'PDC', 'windguru-pdc-001');

INSERT INTO geographical_location (id, name, site, id_windwuru)
VALUES (2, 'Cancún', 'CUN', 'windguru-cun-002');

-- Datos para dive_day
INSERT INTO dive_day (dive_day_id, "day", beginning, "end", site, notes, year, month, assessment, id_geographic, presence_of_jellyfish, water_visibility, sea_background, fish_grass, presence_plastic)
VALUES (1, '2024-11-08', '08:00', '10:00', 'Playa del Carmen', 'Buena visibilidad', '2024', '11', 5, 1, 0, 8, 3, 2, 1);

INSERT INTO dive_day (dive_day_id, "day", beginning, "end", site, notes, year, month, assessment, id_geographic, presence_of_jellyfish, water_visibility, sea_background, fish_grass, presence_plastic)
VALUES (2, '2024-11-09', '09:00', '11:00', 'Cancún', 'Alto oleaje', '2024', '11', 4, 2, 1, 6, 2, 3, 0);

-- Datos para fish
INSERT INTO fish (id, name, site, first_sighting, first_last, start_season, end_season, first_life_warning)
VALUES (1, 'Tiburón', 'PDC', '2022-05-15', '2022-05-20', '2022-05-01', '2022-08-31', 'Alerta temprana');

INSERT INTO fish (id, name, site, first_sighting, first_last, start_season, end_season, first_life_warning)
VALUES (2, 'Delfín', 'CUN', '2023-06-10', '2023-06-15', '2023-06-01', '2023-09-30', 'Ninguna');

-- Datos para fishing
INSERT INTO fishing (id, notes, caught, weight, fish_id, id_geographic, lat_g, long_g, sighting_time, dive_day_id)
VALUES (1, 'Captura exitosa', TRUE, 3.5, 1, 1, 20.6281, -87.0732, '09:30', 1);

INSERT INTO fishing (id, notes, caught, weight, fish_id, id_geographic, lat_g, long_g, sighting_time, dive_day_id)
VALUES (2, 'Sin captura', FALSE, 0.0, 2, 2, 21.1619, -86.8515, '10:45', 2);

-- Datos para wind_conditions
INSERT INTO wind_conditions (year, day_of_year, time_of_day, site, month, "day", wind, wind_direction, gusts_of_wind, wave_height, wave_period, wave_direction, earth_temperature, water_temperature, condition_code, condition_description)
VALUES (2024, 312, 10, 'PDC', 11, 8, 15, 180.0, 20.5, 2.1, 8, 150, 28, 26, 1, 'Brisa suave');

INSERT INTO wind_conditions (year, day_of_year, time_of_day, site, month, "day", wind, wind_direction, gusts_of_wind, wave_height, wave_period, wave_direction, earth_temperature, water_temperature, condition_code, condition_description)
VALUES (2024, 313, 11, 'CUN', 11, 9, 25, 90.0, 30.0, 3.0, 6, 90, 30, 27, 2, 'Viento fuerte');

-- Datos para tide_table
INSERT INTO tide_table (day, month, year, site, moon_phase, coefficient0H, coefficient12H, morning_high_tide_time, morning_high_tide_height, afternoon_high_tide_time, afternoon_high_tide_height, morning_low_tide_time, morning_low_tide_height, afternoon_low_tide_time, afternoon_low_tide_height)
VALUES ('08', '11', '2024', 'PDC', 3, 80, 90, '05:30', 2.5, '17:45', 2.8, '12:00', 0.5, '00:30', 0.4);

INSERT INTO tide_table (day, month, year, site, moon_phase, coefficient0H, coefficient12H, morning_high_tide_time, morning_high_tide_height, afternoon_high_tide_time, afternoon_high_tide_height, morning_low_tide_time, morning_low_tide_height, afternoon_low_tide_time, afternoon_low_tide_height)
VALUES ('09', '11', '2024', 'CUN', 1, 70, 85, '06:00', 2.0, '18:00', 2.3, '12:30', 0.6, '01:00', 0.5);

-- Datos para geographical_location adicionales (si es necesario)
INSERT INTO geographical_location (id, name, site, id_windwuru)
VALUES (3, 'Isla Mujeres', 'IM', 'windguru-im-003');

-- Datos adicionales para fish
INSERT INTO fish (id, name, site, first_sighting, first_last, start_season, end_season, first_life_warning)
VALUES (3, 'Pez León', 'IM', '2023-07-20', '2023-07-25', '2023-07-01', '2023-10-31', 'Invasor');

-- Datos adicionales para fishing
INSERT INTO fishing (id, notes, caught, weight, fish_id, id_geographic, lat_g, long_g, sighting_time, dive_day_id)
VALUES (3, 'Avistamiento raro', FALSE, 0.0, 3, 3, 21.2579, -86.7468, '11:00', 1);
