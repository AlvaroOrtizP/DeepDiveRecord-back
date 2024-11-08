-- Tabla geographical_location
CREATE TABLE geographical_location (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    site VARCHAR(40) NOT NULL,
    id_windwuru VARCHAR(50) NOT NULL
);

-- Tabla dive_day
CREATE TABLE dive_day (
    dive_day_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    "day" VARCHAR(255),
    beginning VARCHAR(255),
    "end" VARCHAR(255),
    site VARCHAR(255),
    notes VARCHAR(255),
    "year" VARCHAR(255),
    "month" VARCHAR(255),
    assessment INTEGER,
    id_geographic INTEGER NOT NULL,
    presence_of_jellyfish INTEGER,
    water_visibility INTEGER,
    sea_background INTEGER,
    fish_grass INTEGER,
    presence_plastic INTEGER,
    FOREIGN KEY (id_geographic) REFERENCES geographical_location(id)
);

-- Tabla fish
CREATE TABLE fish (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    site VARCHAR(255),
    first_sighting VARCHAR(255),
    first_last VARCHAR(255),
    start_season VARCHAR(255),
    end_season VARCHAR(255),
    first_life_warning VARCHAR(255)
);

-- Tabla fishing
CREATE TABLE fishing (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    notes VARCHAR(255),
    caught BOOLEAN,
    weight DECIMAL(10, 2),
    fish_id INTEGER,
    id_geographic INTEGER NOT NULL,
    lat_g DOUBLE,
    long_g DOUBLE,
    sighting_time VARCHAR(255),
    dive_day_id INTEGER NOT NULL,
    FOREIGN KEY (fish_id) REFERENCES fish(id),
    FOREIGN KEY (id_geographic) REFERENCES geographical_location(id),
    FOREIGN KEY (dive_day_id) REFERENCES dive_day(dive_day_id)
);

-- Tabla wind_conditions
CREATE TABLE wind_conditions (
  "year" INTEGER,
  day_of_year INTEGER,
  time_of_day INTEGER,
  site VARCHAR(255),
  "month" INTEGER,
  "day" INTEGER,
  wind INTEGER,
  wind_direction DECIMAL(10, 2),
  gusts_of_wind DOUBLE,
  wave_height DOUBLE,
  wave_period INTEGER,
  wave_direction INTEGER,
  earth_temperature INTEGER,
  water_temperature INTEGER,
  condition_code INTEGER,
  condition_description VARCHAR(255),
  PRIMARY KEY ("year", day_of_year, time_of_day, site)
);

-- Tabla tide_table
CREATE TABLE tide_table (
    `day` VARCHAR(255),
    month VARCHAR(255),
    `year` VARCHAR(255),
    site VARCHAR(255),
    moon_phase INTEGER,
    coefficient0H INTEGER,
    coefficient12H INTEGER,
    morning_high_tide_time VARCHAR(255),
    morning_high_tide_height DOUBLE,
    afternoon_high_tide_time VARCHAR(255),
    afternoon_high_tide_height DOUBLE,
    morning_low_tide_time VARCHAR(255),
    morning_low_tide_height DOUBLE,
    afternoon_low_tide_time VARCHAR(255),
    afternoon_low_tide_height DOUBLE,
    PRIMARY KEY (`day`, month, `year`, site)
);
