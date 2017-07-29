create table planet_types (
  id serial,
  name varchar(32),
  primary key (id)
);

create table planets (
  id serial,
  name varchar(32),
  number integer unique,
  type_id integer references planet_types (id),
  radius integer,
  primary key (id)
);

create table satellites (
  id serial,
  name varchar(32),
  planet_id integer references planets (id),
  primary key (id)
);


   
