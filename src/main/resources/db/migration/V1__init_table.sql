CREATE TABLE category (
  id bigint NOT NULL IDENTITY(1,1),
  name varchar(100) NOT NULL,
  code varchar(50) NOT NULL,
  status varchar(50),
  CONSTRAINT PK_Category_id PRIMARY KEY (id)
);

CREATE TABLE manufacturer (
  id bigint NOT NULL IDENTITY(1,1),
  name varchar(100) NOT NULL,
  code varchar(50) NOT NULL,
  CONSTRAINT PK_Manufacturer_id PRIMARY KEY (id)
);

CREATE TABLE country (
  id bigint IDENTITY(1,1) NOT NULL,
  name varchar(100) NOT NULL,
  code varchar(50) NOT NULL,
  CONSTRAINT PK_Country_id PRIMARY KEY (id)
);