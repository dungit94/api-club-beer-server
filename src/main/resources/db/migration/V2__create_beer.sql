CREATE TABLE beer (
  id bigint IDENTITY(1,1) NOT NULL,
  name varchar(100) NOT NULL,
  code varchar(50) NOT NULL,
  price bigint DEFAULT 0,
  description varchar(255) ,
  category_id bigint NOT NULL,
  country_id bigint NOT NULL,
  manufacturer_id bigint NOT NULL,
  CONSTRAINT PK_Beer_id PRIMARY KEY (id)
);