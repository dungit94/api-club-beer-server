CREATE TABLE category (
  id bigint NOT NULL IDENTITY(1,1),
  name varchar(100) NOT NULL,
  code varchar(50) NOT NULL,
  status varchar(50),
);

CREATE TABLE manufacturer (
  id bigint NOT NULL IDENTITY(1,1),
  name varchar(100) NOT NULL,
  code varchar(50) NOT NULL,
);

CREATE TABLE country (
  id bigint IDENTITY(1,1) NOT NULL,
  name varchar(100) NOT NULL,
  code varchar(50) NOT NULL,
);