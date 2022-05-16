CREATE TABLE beer_authority (
  id bigint NOT NULL IDENTITY(1,1),
  name varchar(100) NOT NULL
);

CREATE TABLE beer_user (
   id bigint NOT NULL IDENTITY(1,1),
   email varchar(255) NOT NULL,
   password varchar(255) NOT NULL,
   authority varchar(100) NOT NULL,
   is_activated int DEFAULT 1,
);