create table company (
  id        bigserial PRIMARY KEY,
  name      varchar(40) NOT NULL,
  size_type varchar(10) NOT NULL
);

create table company_action (
  id          bigserial PRIMARY KEY,
  company_id  bigint references company (id),
  change      timestamp   NOT NULL,
  action_type varchar(20) NOT NULL
);

create table goods (
  id                    bigserial PRIMARY KEY,
  company_id            bigint references company (id),
  name                  varchar(70) NOT NULL,
  placement_type        varchar(20) NOT NULL,
  measurement_unit_type varchar(20) NOT NULL,
  cost                  integer     NOT NULL,
  weight                real        NOT NULL,
  labelling             varchar(50) NOT NULL,
  description           text,
  deleted               date
);

create table address (
  id       bigserial PRIMARY KEY,
  country  varchar(30) NOT NULL,
  region   varchar(50) NOT NULL,
  locality varchar(50) NOT NULL
);

create table "user" (
  id                    bigserial PRIMARY KEY,
  company_id            bigint references company (id),
  address_id            bigint references address (id),
  username              varchar(50)  NOT NULL UNIQUE,
  password              varchar(100) NOT NULL,
  first_name            varchar(30)  NOT NULL,
  last_name             varchar(30)  NOT NULL,
  patronymic            varchar(255),
  enabled               boolean,
  lastpasswordresrtdate timestamp,
  birth                 date         NOT NULL,
  email                 varchar(50),
  deleted               date
);

create table authority (
  id   bigserial PRIMARY KEY,
  name varchar(50) NOT NULL
);

create table user_authority (
  user_id      bigint references "user" (id),
  authority_id bigint references authority (id),
  PRIMARY KEY (user_id, authority_id)
);


create table counterparty (
  id                bigserial PRIMARY KEY,
  address_id        bigint references address (id),
  company_id        bigint references company (id),
  name              varchar(50) NOT NULL,
  counterparty_type varchar(15) NOT NULL,
  tax_number        varchar(15) NOT NULL,
  deleted           date
);

create table carrier (
  id           bigserial PRIMARY KEY,
  address_id   bigint references address (id),
  company_id   bigint references company (id),
  name         varchar(50) NOT NULL,
  carrier_type varchar(20) NOT NULL,
  tax_number   varchar(15) NOT NULL,
  trusted      boolean NOT NULL,
  deleted      date
);

create table driver (
  id         bigserial PRIMARY KEY,
  carrier_id bigint references carrier (id),
  info       varchar(70) NOT NULL,
  deleted    date
);

create table warehouse (
  id         bigserial PRIMARY KEY,
  company_id bigint references company (id),
  name       varchar(20),
  deleted    date
);

create table placement (
  id                    bigserial PRIMARY KEY,
  warehouse_id          bigint references warehouse (id),
  placement_type        varchar(20) NOT NULL,
  storage_cost          integer     NOT NULL,
  size                  integer     NOT NULL,
  measurement_unit_type varchar(20) NOT NULL,
  deleted               date
);

create table consignment_note (
  id                    bigserial PRIMARY KEY,
  company_id            bigint references company (id),
  carrier_id            bigint references carrier (id),
  counterparty_id       bigint references counterparty (id),
  creator_id            bigint references "user" (id),
  number                varchar(15) NOT NULL,
  consignment_note_type varchar(5)  NOT NULL,
  shipment              date        NOT NULL,
  registration          date        NOT NULL,
  vehicle_number        varchar(15) NOT NULL
);

create table commodity_lot (
  id                 bigserial PRIMARY KEY,
  company_id         bigint references company (id),
  counterparty_id    bigint references counterparty (id),
  creation           date        NOT NULL,
  commodity_lot_type varchar(20) NOT NULL
);

create table write_off_act (
  id                 bigserial PRIMARY KEY,
  company_id         bigint references company (id),
  creator_id         bigint references "user" (id),
  write_off_act_type varchar(20)  NOT NULL,
  creation           date         NOT NULL,
  total_amount       integer      NOT NULL,
  responsible_person varchar(100) NOT NULL
);

create table consignment_note_goods (
  id                  bigserial PRIMARY KEY,
  goods_id            bigint references goods (id),
  consignment_note_id bigint references consignment_note (id),
  amount              integer NOT NULL
);

create table write_off_act_goods (
  id               bigserial PRIMARY KEY,
  goods_id         bigint references goods (id),
  write_off_act_id bigint references write_off_act (id),
  write_off_type   varchar(20) NOT NULL,
  amount           integer     NOT NULL
);

create table commodity_lot_goods (
  id               bigserial PRIMARY KEY,
  goods_id         bigint references goods (id),
  commodity_lot_id bigint references commodity_lot (id),
  amount           integer NOT NULL
);

create table placement_goods (
  id                bigserial PRIMARY KEY,
  goods_id          bigint references goods (id),
  placement_id      bigint references placement (id),
  counterparty_id   bigint references counterparty (id),
  amount            integer NOT NULL,
  storage_time_days integer NOT NULL,
  deleted           date
);
