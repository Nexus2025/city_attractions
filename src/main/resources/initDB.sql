DROP TABLE IF EXISTS attractions;
DROP TABLE IF EXISTS cities;

CREATE TABLE cities (
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR NOT NULL,
    population          INTEGER NOT NULL,
    subway_availability BOOLEAN NOT NULL,
    country             VARCHAR NOT NULL,

    CONSTRAINT name_country_unique_idx UNIQUE (name, country)
);

CREATE TABLE attractions (
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR NOT NULL,
    construction_date   DATE NOT NULL,
    description         VARCHAR NOT NULL,
    type                VARCHAR NOT NULL,
    city_id             INTEGER NOT NULL,

    CONSTRAINT name_city_id_unique_idx UNIQUE (name, city_id),
    FOREIGN KEY (city_id) REFERENCES cities (id) ON DELETE CASCADE
);