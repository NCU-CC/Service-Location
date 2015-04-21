
INSERT INTO Person (id, personal_no, cname, ename, title, primary_unit_no, secondary_unit_no, office_phone) VALUES
(1, 'P1', 'CPERSON1', NULL, 'TITLE1', 'A100', 'A110', NULL),
(2, 'P2', 'CPERSON2', 'EPERSON2', 'TITLE2', 'A100', 'A110', '100');

INSERT INTO Place (id, cname, ename, picture_name, type, location) VALUES
(1, 'CPLACE1', 'EPLACE1', NULL, 'WHEELCHAIR_RAMP', NULL),
(2, 'CPLACE2', 'EPLACE2', NULL, 'WHEELCHAIR_RAMP', NULL),
(3, 'CPLACE3', 'EPLACE3', NULL, 'RESEARCH', NULL);

INSERT INTO Unit (unit_no, cname, ename, short_name, full_name, url, location, modify_date) VALUES
('A100', 'CUNIT1', 'EUNIT1', 'U1', 'FUNIT1', 'http://www.example.com', NULL, '2015-01-01 00:00:00'),
('A110', 'CUNIT2', 'EUNIT2', 'U2', 'FUNIT2', 'http://www.example.com', NULL, '2014-01-01 00:00:00');

INSERT INTO Building_Unit (id, place_id, unit_name, unit_no) VALUES
(1, 1, 'CUNIT1', NULL),
(2, 2, 'CUNIT2', 'A110');