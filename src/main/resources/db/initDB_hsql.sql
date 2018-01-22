DROP TABLE user_roles IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE users_vote IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP TABLE users IF EXISTS;

DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL,
  email            VARCHAR(255)            NOT NULL,
  password         VARCHAR(255)            NOT NULL,
  registered       DATE                    NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name        VARCHAR(30)  NOT NULL,
  description VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE menu
(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  date            DATE DEFAULT now()    NOT NULL,
  restaurant_id   INTEGER               NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_unique_restaurant_id_idx ON menu (restaurant_id, date);

CREATE TABLE users_vote
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  user_id         INTEGER              NOT NULL,
  date            DATE DEFAULT now()   NOT NULL,
  restaurant_id   INTEGER              NOT NULL,
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_vote_unique_date_time_idx ON users_vote (date, user_id);

CREATE TABLE dishes
(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name            VARCHAR(100)  NOT NULL,
  price           DOUBLE        NOT NULL,
  menu_id         INTEGER       NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dishes_unique_menu_id_idx ON dishes (menu_id, name);