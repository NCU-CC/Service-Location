
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

INSERT INTO Person (id, personal_no, cname, ename, title, primary_unit_no, secondary_unit_no, office_phone) VALUES
(1, 'P1', 'CPERSON1', NULL, 'TITLE1', 'A100', 'A110', NULL),
(2, 'P2', 'CPERSON2', 'EPERSON2', 'TITLE2', 'A100', 'A110', '100');

CREATE TABLE IF NOT EXISTS Place (
  id            int(11)         NOT NULL,
  cname         varchar(80)     DEFAULT NULL,
  ename         varchar(100)    DEFAULT NULL,
  pictureName   varchar(40)     DEFAULT NULL,
  type          varchar(30)     NOT NULL,
  location      VARBINARY(255)  DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO Place (id, cname, ename, pictureName, type, location) VALUES
(1, 'CPLACE1', 'EPLACE1', NULL, 'WHEELCHAIR_RAMP', NULL),
(2, 'CPLACE2', 'EPLACE2', NULL, 'WHEELCHAIR_RAMP', NULL);

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

INSERT INTO Unit (unit_no, cname, ename, short_name, full_name, url, location, modify_date) VALUES
('A100', 'CUNIT1', 'EUNIT1', 'U1', 'FUNIT1', 'http://www.example.com', NULL, '2015-01-01 00:00:00'),
('A110', 'CUNIT2', 'EUNIT2', 'U2', 'FUNIT2', 'http://www.example.com', NULL, '2014-01-01 00:00:00');

CREATE TABLE IF NOT EXISTS Building_Unit (
  id          int(11)     NOT NULL,
  place_id    int(11)     NOT NULL,
  unit_name   varchar(80) NOT NULL,
  unit_no     varchar(10) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO Building_Unit (id, place_id, unit_name, unit_no) VALUES
(1, 1, 'CUNIT1', NULL),
(2, 2, 'CUNIT2', 'A110');