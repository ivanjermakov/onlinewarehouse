### Как пишем 
Будем пробовать писать на подходе `feature level` т.е. группирововать по фичам в пакеты(кто помнит - недавняя лекция Сергея по спрингу когда он показывал примеры проектов).
Тесты нужны, если красиво, то вообще TDD, но там уже как пойдет.
Каждую таску делаем в отдельной ветке, в описании коммита указываем номер таски, думаю будет достаточно. Потом мержим в dev. 
### Предложение по наименованию сущностей
Carrier - Перевозчик;
CommodityLot - Товарная партия;
CLGoods - Товар товарной партии;
Company - Компания клиент
ConsignmentNote - ТТН;
CNGoods - Товар ТТН;
Counterparty - Контрагент;
Driver - Водитель;
Goods - Товар;
Placement - Помещение;
PGoods - Товар помещения;
Role - Роль;
User - Пользователь;
Warehouse - Склад;
WriteOffAct - Акт списания;
AGoods - Товар акта.

Описание полей в самих сущностях в тасках.

### Примерное описание бизнес-логики движения товара
1. От отправителя поступает заказ на хранение товара и он отправляет товар – условно, никак не связано с системой онлайн склад.
2. Приходит условный водитель с ТТН и диспетчер склада начинает заполнять ТТН в системе. Он указывает товары и их количество. ///где-то тут должна быть проверка на количество товара
3. В момент регистрации ТТН данные о товаре вносятся в таблицу «Товар» (только основные характеристики без количества) и «Товар ТТН»(количество и ссылка на товар), статус ТТН становаться «Зарегистрирована». ТТН помечается как «входящая»
4. Контролер склада видит зарегистрированную ТТН и начинает проверку поступившего товара.
4.1. В данном случае во время доставки водителем была утеряна часть товара и на эту часть контролер составляет акт (Условная кнопка создать акт). В акте контролер выбирает по товарам указанным из ТТН и указывает для каждого количество недостачи и причину. На момент сохранения сохраняется акт и в отдельную таблицу товар акта (каждая строка содержит ссылку на товар, количество и причину утраты). 
4.2. В данном случае с партией все хорошо и он нажимает кнопку «партия проверена». 
5. По результатом предыдущего этапа в таблице «Товарная партия» создается строка о данной партии, а в таблицу «Товары товарной партии» вносятся значения о фактическом количестве поступивших товаров (4.1 – ТТН-акт, 4.2 – по ТТН).
6. Сведения о данной партии появляются у менеджера и он занимается распределением товара по помещениям склада. У каждого помещения есть «Товары помещения» - там ссылка на соответствующий товар из таблицы «Товар», количество, и срок хранения этого товара.
По сути товар помещения – есть фактическое количество товаров на складе.
7. В период до отправки товара со склада также может быть составлен акт, по факту его составления мы отнимаем от количества «Товаров помещения» соответствующие значения.
8. Менеджеру склада поступает условная заявка на товары (которая не задействована в нашей системе) и менеджер оформляет ТТН. Процесс аналогичен шагам 2-3, за исключением того, что вносятся значения в таблицу «Товар». ТТН помечается как «исходящая».
9. Контролер видит созданную ТТН и идет сверять товар. Тут может быть составлен акт на то что товаров в таком количестве нет, (процедура для акта обычная) и тогда в товарную партию отгружается весь товар который есть. По сути аналогично с пунктом 4 (2 кнопки «отправить товар» или «создать акт и отправить товар»). После нажатия на кнопку сохранить «для акта или отправить товар» создается товарная партия которая по сути отражает количество фактически отравленного товара. Процесс аналогичен пункту 5.
10. На этом работа системы «Онлайн-склад окончена»
