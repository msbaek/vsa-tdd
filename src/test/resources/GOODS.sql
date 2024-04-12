create table if not exists goods
(
    goods_no bigint comment '상품no' primary key,
    goods_id varchar(20)              null comment '상품id',
    barcode  varchar(100)             null comment '바코드'
);
delete from goods_collection_item where 1 = 1;
delete from goods_collection where 1 = 1;
delete from goods where 1 = 1;
insert into goods (goods_no, goods_id, barcode) values (112296, 'GD00112296', '9000000112296') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112297, 'GD00112297', '9000000112297') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112298, 'GD00112298', '9000000112298') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112299, 'GD00112299', '9000000112299') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112300, 'GD00112300', '9000000112300') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112301, 'GD00112301', '9000000112300') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112303, 'GD00112303', '9000000112303') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112304, 'GD00112304', '9000000112304') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112306, 'GD00112306', '9000000112306') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
insert into goods (goods_no, goods_id, barcode) values (112307, 'GD00112307', '9000000112307') on duplicate key update goods_id=values(goods_id), barcode=values(barcode);
