CREATE TABLE authority (
  id bigint NOT NULL IDENTITY(1,1),
  name varchar(100) NOT NULL,
  CONSTRAINT PK_Authority_id PRIMARY KEY CLUSTERED (id)
);

CREATE TABLE user (
   id bigint NOT NULL IDENTITY(1,1),
   login varchar(100) NOT NULL,
   password varchar(100) NOT NULL,
   first_name varchar(100) NOT NULL,
   last_name varchar(100) NOT NULL,
   email varchar(100) NOT NULL,
   activated int,
   activation_key varchar(100) NOT NULL,
   reset_date varchar(100) NOT NULL,
   lang_key varchar(100) NOT NULL,
   CONSTRAINT PK_Authority_id PRIMARY KEY CLUSTERED (id)
);