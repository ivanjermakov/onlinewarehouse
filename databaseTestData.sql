-- Создаем свою же компанию, должно быть в скрипте базы
INSERT INTO company (name, size_type) values ('Online warehouse Company', 'SMALL');

-- Добавляем роли, должно быть в скрипте базы
INSERT INTO role (role) VALUES ('ADMIN');
INSERT INTO role (role) VALUES ('COMPANY_ADMIN');
INSERT INTO role (role) VALUES ('DISPATCHER');
INSERT INTO role (role) VALUES ('MANAGER');
INSERT INTO role (role) VALUES ('INSPECTOR');

-- Добавляем админа всей системы, должно быть в скрипте базы
INSERT INTO address (country, region, locality) VALUES ('Admin', 'Admin', 'Admin');
INSERT INTO "user" (company_id, address_id, first_name, last_name, patronymic, birth, email) VALUES (1, 1, 'Admin', 'Admin', 'Admin', '2018-10-10', 'companyemail@mail.ru');

-- Зарегали компанию
INSERT INTO company (name, size_type) values ('First Company', 'LARGE');
-- одноверменно указали что создали
INSERT INTO company_action (company_id, change, action_type) VALUES (1, current_timestamp, 'ENABLED');
-- одновременно создали админа компании
INSERT INTO address (country, region, locality) VALUES ('Belarus', 'Minsk', 'Tolstogo str. 10');
INSERT INTO "user" (company_id, address_id, first_name, last_name, patronymic, birth, email) VALUES (2, 2, 'Kaskin', 'Iliya', 'Ivanovich', '1987-10-17', 'testemail1@mail.ru');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

--админ создал работников в своей компании и присвоил роли
--первый
INSERT INTO address (country, region, locality) VALUES ('Belarus', 'Minsk', 'Nezavisimosti str. 158');
INSERT INTO "user" (company_id, address_id, first_name, last_name, patronymic, birth, email) VALUES (2, 3, 'Dzerginsky', 'Nikolay', 'Valeryevich', '1961-03-12', 'testemail2@mail.ru');
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 3);
--второй
INSERT INTO address (country, region, locality) VALUES ('Belarus', 'Minsk', 'Timiryazeva str. 35');
INSERT INTO "user" (company_id, address_id, first_name, last_name, patronymic, birth, email) VALUES (2, 4, 'Litvinyk', 'Aleksey', 'Dmitrievich', '1993-05-03', 'testemail3@mail.ru');
INSERT INTO user_role (user_id, role_id) VALUES (3, 4);
INSERT INTO user_role (user_id, role_id) VALUES (3, 5);

--админ компании, возможно менеджер, создал склад и указал помещения в нем
INSERT INTO warehouse (company_id, name) values (2, 'First warehouse');
INSERT INTO placement (warehouse_id, placement_type, storage_cost, "size", measurement_unit_type) VALUES (1, 'FREEZER', 5, 100, 'TEST');
INSERT INTO placement (warehouse_id, placement_type, storage_cost, "size", measurement_unit_type) VALUES (1, 'UNHEATED', 2, 100, 'TEST');

--кто-то из работников предварительно добавил в систему перевозчика и отправителя
INSERT INTO address (country, region, locality) VALUES ('Belarus', 'Minsk', 'Carrier Tolstogo str. 10');
INSERT INTO carrier (address_id, company_id, name, carrier_type, tax_number, trusted) VALUES (5, 2, 'Carrier 1', 'AUTOMOBILE', 'sdfsfdf', false);
INSERT INTO address (country, region, locality) VALUES ('Belarus', 'Minsk', 'Counterparty Tolstogo str. 10');
INSERT INTO counterparty (address_id, company_id, name, counterparty_type, tax_number) VALUES (6, 2, 'Counterparty 1', 'CONSIGNOR', 'assfdfdssdf');

--пришла ТТН, диспетчер регистрирует ее (и отправитель, и перевозчик уже были в базе)
INSERT INTO consignment_note (company_id, carrier_id, counterparty_id, creator_id, number, consignment_note_type, shipment, registration, vehicle_number) VALUES (2, 1, 1, 3, '12302143', 'IN', '2018-10-23', '2018-10-24', '1754-ВС 7');
-- товары которые были в ТТН, добавляем в справочник
INSERT INTO goods (company_id, name, placement_type, measurement_unit_type, cost, weight, labelling, description) VALUES (2, 'Some goods 1 name', 'FREEZER', 'TEST', 10, 1, 'some label', 'some description');
INSERT INTO goods (company_id, name, placement_type, measurement_unit_type, cost, weight, labelling, description) VALUES (2, 'Some goods 2 name', 'UNHEATED', 'TEST', 1, 0.2, 'some label', 'some description');
INSERT INTO goods (company_id, name, placement_type, measurement_unit_type, cost, weight, labelling, description) VALUES (2, 'Some goods 3 name', 'UNHEATED', 'TEST', 3, 15, 'some label', 'some description');
-- и добавляем в список к ТТНке
INSERT INTO consignment_note_goods (goods_id, consignment_note_id, amount) VALUES (1, 1, 10);
INSERT INTO consignment_note_goods (goods_id, consignment_note_id, amount) VALUES (2, 1, 15);
INSERT INTO consignment_note_goods (goods_id, consignment_note_id, amount) VALUES (3, 1, 6);

--Контролер увидел созданную ТТНку и пошел проверять, нашел косяк, нету 1 товара "Some goods 1 name" из 10 и создает акт
INSERT INTO write_off_act (company_id, creator_id, write_off_act_type, creation, total_amount, responsible_person) VALUES (2, 3, 'SHORTFALL', '2018-10-24', 10, 'Somebody');
-- указываем количество товаров и причину (на TEST забейте, нужно статусы описать)
INSERT INTO write_off_act_goods(goods_id, write_off_act_id, write_off_type, amount) VALUES (1, 1, 'CARRIER_LOSS', 1);
--одновременно создается товарая партия
INSERT INTO commodity_lot (company_id, counterparty_id, creation, commodity_lot_type) VALUES (2, 1, '2018-10-24', 'IN');
--c товарами (вычитаем единицу товара "Some goods 1 name" из акта итого 9 товара "Some goods 1 name")
INSERT INTO commodity_lot_goods (goods_id, commodity_lot_id, amount) VALUES (1, 1, 9);
INSERT INTO commodity_lot_goods (goods_id, commodity_lot_id, amount) VALUES (2, 1, 15);
INSERT INTO commodity_lot_goods (goods_id, commodity_lot_id, amount) VALUES (3, 1, 6);

--менеджер видит товарную партию и начинает расскидывать ее по складу(заказ на хранение был на 10 дней)
INSERT INTO placement_goods (goods_id, placement_id, counterparty_id, amount, storage_time_days) VALUES (1, 1, 1, 9, 10);
INSERT INTO placement_goods (goods_id, placement_id, counterparty_id, amount, storage_time_days) VALUES (2, 2, 1, 15, 10);
INSERT INTO placement_goods (goods_id, placement_id, counterparty_id, amount, storage_time_days) VALUES (3, 2, 1, 6, 10);

--контролер решил перепроверить склад и обнаружил что кто-то украл 2 единицы товара 'Some goods 1 name' и составляет акт
INSERT INTO write_off_act (company_id, creator_id, write_off_act_type, creation, total_amount, responsible_person) VALUES (2, 3, 'LOSS', '2018-10-24', 20, 'Somebody');
-- указываем количество товаров и причину (на TEST забейте, нужно статусы описать)
INSERT INTO write_off_act_goods(goods_id, write_off_act_id, write_off_type, amount) VALUES (1, 1, 'WAREHOUSE_THEFT', 2);
-- и тут должна заапдейтиться таблица с товарами на складе (9 - 2 = 7)
UPDATE placement_goods SET amount = 7 WHERE goods_id = 1 AND placement_id = 1 AND counterparty_id = 1 AND storage_time_days = 10;

--товар лежит и тут его кто-то хочет забрать
--менеджер создает делает ТТН на выпуск, таких перевозчика и получателя в системе нет и он их предварительно регистрирует (прям в окошке создания ТТН)
-- перевозчика
INSERT INTO address (country, region, locality) VALUES ('Belarus', 'Minsk', 'Carrier Nezavisimosti str. 158');
INSERT INTO carrier (address_id, company_id, name, carrier_type, tax_number, trusted) VALUES (7, 2, 'Carrier 2', 'AUTOMOBILE', 'asd', false);
--получатель
INSERT INTO address (country, region, locality) VALUES ('Belarus', 'Minsk', 'Counterparty Nezavisimosti str. 158');
INSERT INTO counterparty (address_id, company_id, name, counterparty_type, tax_number) VALUES (8, 2, 'Counterparty 1', 'CONSIGNEE', 'assfdfdssdf');
--сама ТТН
INSERT INTO consignment_note (company_id, carrier_id, counterparty_id, creator_id, number, consignment_note_type, shipment, registration, vehicle_number)
VALUES (1, 2, 2, 4, '123124432', 'OUT', '2018-10-28', '2018-10-28', '1234-КР 7');
--и товары к ней
INSERT INTO consignment_note_goods (goods_id, consignment_note_id, amount) VALUES (1, 2, 7);
INSERT INTO consignment_note_goods (goods_id, consignment_note_id, amount) VALUES (2, 2, 10);

-- контролер идет проверять наличие товара, все ок, и он создает товарную партию
INSERT INTO commodity_lot (company_id, counterparty_id, creation, commodity_lot_type) VALUES (2, 2, '2018-10-28', 'OUT');
--c товарами (вычитаем единицу товара "Some goods 1 name" из акта итого 9 товара "Some goods 1 name")
INSERT INTO commodity_lot_goods (goods_id, commodity_lot_id, amount) VALUES (1, 2, 7);
INSERT INTO commodity_lot_goods (goods_id, commodity_lot_id, amount) VALUES (2, 2, 10);
--одновременно обновляем значения на складе
UPDATE placement_goods SET amount = 0 WHERE goods_id = 1 AND placement_id = 1 AND counterparty_id = 1 AND storage_time_days = 10;
UPDATE placement_goods SET amount = 5 WHERE goods_id = 2 AND placement_id = 2 AND counterparty_id = 1 AND storage_time_days = 10;

-- ну и тип все, на этом цикл закончен

