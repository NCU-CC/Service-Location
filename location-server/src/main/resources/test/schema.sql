
CREATE TABLE IF NOT EXISTS Person (
  id                int(11)       NOT NULL,
  personal_no       varchar(35)   NOT NULL,
  cname             varchar(50)   NOT NULL,
  ename             varchar(50)   DEFAULT NULL,
  title             varchar(100)  NOT NULL,
  primary_unit_no   varchar(10)   NOT NULL,
  secondary_unit_no varchar(10)   NOT NULL,
  office_phone      varchar(50)   DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Place (
  id            int(11)         NOT NULL,
  cname         varchar(80)     DEFAULT NULL,
  ename         varchar(100)    DEFAULT NULL,
  picture_name   varchar(40)     DEFAULT NULL,
  type          varchar(30)     NOT NULL,
  location      VARBINARY(255)  DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Unit (
  unit_no     varchar(10)     NOT NULL,
  cname       varchar(80)     NOT NULL,
  ename       varchar(100)    DEFAULT NULL,
  short_name  varchar(50)     NOT NULL,
  full_name   varchar(100)    NOT NULL,
  url         varchar(255)    DEFAULT NULL,
  location    VARBINARY(255)  DEFAULT NULL,
  modify_date timestamp NOT NULL,
  PRIMARY KEY (unit_no)
);

CREATE TABLE IF NOT EXISTS Building_Unit (
  id          int(11)     NOT NULL,
  place_id    int(11)     NOT NULL,
  unit_name   varchar(80) NOT NULL,
  unit_no     varchar(10) DEFAULT NULL,
  PRIMARY KEY (id)
);