create table _countries (
  id integer PRIMARY KEY,
  title varchar(40) NOT NULL
);

create table _regions (
  id integer PRIMARY KEY,
  country_id integer references _countries (id),
  title varchar(55) NOT NULL
);

create table company (
  id        bigserial PRIMARY KEY,
  name      varchar(40) NOT NULL,
  size_type varchar(10) NOT NULL,
  logo text
);

create table company_action (
  id          bigserial PRIMARY KEY,
  company_id  bigint references company (id),
  date_start  timestamp   NOT NULL,
  date_end    timestamp   NOT NULL
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
  country  varchar(40) NOT NULL,
  region   varchar(55) NOT NULL,
  locality varchar(50) NOT NULL
);

create table users (
  id                    bigserial PRIMARY KEY,
  company_id            bigint references company (id),
  address_id            bigint references address (id),
  username              varchar(50)  NOT NULL UNIQUE,
  password              varchar(100),
  first_name            varchar(30)  NOT NULL,
  last_name             varchar(30)  NOT NULL,
  patronymic            varchar(255),
  enabled               boolean,
  lastpasswordresrtdate timestamp,
  birth                 date         NOT NULL,
  email                 varchar(50),
  activation_code       varchar(255),
  deleted               date
);

create table authority (
  id   bigserial PRIMARY KEY,
  name varchar(50) NOT NULL
);

create table user_authority (
  user_id      bigint references users (id),
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
  address_id bigint references address (id),
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

create table payment_act (
  id           bigserial PRIMARY KEY,
  placement_id bigint references placement (id),
  creation     date    NOT NULL,
  amount integer NOT NULL
);

-- refactor table: add driver_id, consignment_note_status, description
create table consignment_note (
  id                    bigserial PRIMARY KEY,
  company_id            bigint references company (id),
  carrier_id            bigint references carrier (id),
  driver_id             bigint references driver (id),
  counterparty_id       bigint references counterparty (id),
  creator_id            bigint references users (id),
  number                varchar(15) NOT NULL,
  consignment_note_type varchar(5)  NOT NULL,
  shipment              date        NOT NULL,
  registration          date        NOT NULL,
  vehicle_number        varchar(15) NOT NULL,
  consignment_note_status varchar(15) NOT NULL,
  description           text NOT NULL
);

create table commodity_lot (
  id                   bigserial PRIMARY KEY,
  company_id           bigint references company (id),
  counterparty_id      bigint references counterparty (id),
  creation             date        NOT NULL,
  commodity_lot_type   varchar(20) NOT NULL,
  commodity_lot_status varchar(20) NOT NULL
);

create table write_off_act (
  id                 bigserial PRIMARY KEY,
  company_id         bigint references company (id),
  creator_id         bigint references users (id),
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
  expiration_date   date NOT NULL,
  deleted           date
);

create table birthday_mail_template (
  id bigserial primary key,
  company_id bigint references company (id),
  text varchar not null,
  background_color varchar(7) not null,
  header_image_path varchar
);

create table birthday_mail_send (
  id bigserial primary key,
  user_id bigint references users (id),
  timestamp date not null,
  successful boolean not null
);

create table home_card (
  id         bigserial PRIMARY KEY,
  company_id bigint references company (id),
  title      varchar(50) NOT NULL,
  src        varchar(80),
  content    text        NOT NULL
);