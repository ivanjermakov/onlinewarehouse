create table company (
  id               bigserial PRIMARY KEY,
  company_size     varchar(10) NOT NULL,
  create_date_time timestamp   NOT NULL,
  work_status      varchar(10) NOT NULL
);

create table goods (
  id             bigserial PRIMARY KEY,
  cost           integer     NOT NULL,
  description    text        NOT NULL,
  labelling      varchar(50) NOT NULL,
  name           varchar(70) NOT NULL,
  placement_type varchar(20) NOT NULL,
  weight         real        NOT NULL,
  company_id     bigint REFERENCES company (id)
);

create table "user" (
  id          bigserial PRIMARY KEY,
  city        varchar(50) NOT NULL,
  street      varchar(50) NOT NULL,
  house       varchar(15) NOT NULL,
  flat        varchar(15),
  birth_date  date        NOT NULL,
  email       varchar(50) NOT NULL,
  first_name  varchar(30) NOT NULL,
  last_name   varchar(30) NOT NULL,
  middle_name varchar(255),
  login       varchar(30) NOT NULL,
  password    varchar(30) NOT NULL,
  company_id  bigint REFERENCES company (id)
);

create table role (
  id   bigserial PRIMARY KEY,
  role varchar(50) NOT NULL
);

create table user_role (
  user_id bigint REFERENCES "user" (id),
  role_id bigint REFERENCES role (id),
  PRIMARY KEY (user_id, role_id)
);

create table counterparty (
  id                bigserial PRIMARY KEY,
  city              varchar(50) NOT NULL,
  street            varchar(50) NOT NULL,
  house             varchar(15) NOT NULL,
  flat              varchar(15),
  counterparty_type varchar(15) NOT NULL,
  name              varchar(50) NOT NULL,
  tax_number        varchar(15) NOT NULL,
  company_id        bigint REFERENCES company (id)
);

create table carrier (
  id           bigserial PRIMARY KEY,
  city         varchar(50) NOT NULL,
  street       varchar(50) NOT NULL,
  house        varchar(15) NOT NULL,
  flat         varchar(15),
  carrier_type varchar(20) NOT NULL,
  name         varchar(50) NOT NULL,
  tax_number   varchar(15) NOT NULL,
  company_id   bigint REFERENCES company (id)
);

create table driver (
  id          bigserial PRIMARY KEY,
  driver_info varchar(70) NOT NULL,
  carrier_id  bigint REFERENCES carrier (id)
);

create table warehouse (
  id         bigserial PRIMARY KEY,
  company_id bigint REFERENCES company (id)
);

create table placement (
  id                  bigserial PRIMARY KEY,
  cost_of_storage     integer     NOT NULL,
  placement_type      varchar(20) NOT NULL,
  size                integer     NOT NULL,
  unit_of_measurement varchar(20) NOT NULL,
  warehouse_id        bigint REFERENCES warehouse (id)
);

create table consignment_note (
  id                     bigserial PRIMARY KEY,
  consignment_note_type  varchar(5)  NOT NULL,
  create_date            date        NOT NULL,
  number                 varchar(15) NOT NULL,
  registration_date_time timestamp   NOT NULL,
  vehicle_number         varchar(15) NOT NULL,
  carrier_id             bigint REFERENCES carrier (id),
  company_id             bigint REFERENCES company (id),
  counterparty_id        bigint REFERENCES counterparty (id),
  creator_id             bigint REFERENCES "user" (id)
);

create table commodity_lot (
  id                 bigserial PRIMARY KEY,
  commodity_lot_type varchar(20) NOT NULL,
  company_id         bigint REFERENCES company (id),
  counterparty_id    bigint REFERENCES counterparty (id)
);

create table write_off_act (
  id                 bigserial PRIMARY KEY,
  create_date        date         NOT NULL,
  responsible_person varchar(100) NOT NULL,
  total_amount       integer      NOT NULL,
  write_off_act_type varchar(20)  NOT NULL,
  company_id         bigint REFERENCES company (id),
  creator_id         bigint REFERENCES "user" (id)
);

create table c_n_goods (
  id                  bigserial PRIMARY KEY,
  count               integer   NOT NULL,
  goods_id            bigint REFERENCES goods (id),
  consignment_note_id bigint REFERENCES consignment_note (id)
);

create table a_goods (
  id               bigserial PRIMARY KEY,
  count            integer     NOT NULL,
  write_off_type   varchar(20) NOT NULL,
  goods_id         bigint REFERENCES goods (id),
  write_off_act_id bigint REFERENCES write_off_act (id)
);

create table c_l_goods (
  id               bigserial PRIMARY KEY,
  count            integer NOT NULL,
  goods_id         bigint REFERENCES goods (id),
  commodity_lot_id bigint REFERENCES commodity_lot (id)
);

create table p_goods (
  id                bigserial PRIMARY KEY,
  count             integer     NOT NULL,
  storage_time_days integer     NOT NULL,
  goods_id          bigint REFERENCES goods (id),
  placement_id      bigint REFERENCES placement (id)
);
