

# --- !Ups

insert into department (id,department) values (1,'Production');
insert into department (id,department) values (2,'Research and Development' );
insert into department (id,department) values (3,'Purchasing');
insert into department (id,department) values (4,'Marketing');
insert into department (id,department) values (5,'Human resource Management');
insert into department (id,department) values (6,'Acounting and Finance');
insert into department (id,department) values (7,'Administration');
insert into department (id,department) values (8,'Sales');

insert into employee (id,department_id,employee,address) values ( 1,1,'John Smith','Dublin' );
insert into employee (id,department_id,employee,address) values ( 2,5,'Conor Dickinson','Dublin');
insert into employee (id,department_id,employee,address) values ( 3,3,'Aoife Harrington','Wicklow' );
insert into employee (id,department_id,employee,address) values ( 4,6,'Sinead Pitman','Kildare' );
insert into employee (id,department_id,employee,address) values ( 5,8,'Daniel Finnegan','Mayo' );
insert into employee (id,department_id,employee,address) values ( 6,7,'Brendan Paxton','Dublin');
insert into employee (id,department_id,employee,address) values ( 7,4,'Marie Andrews','Dublin' );
insert into employee (id,department_id,employee,address) values ( 8,2,'Leigh-Anne Vulfpeck','Kildare' );
insert into employee (id,department_id,employee,address) values ( 9,3,'Mac Dean','Cork' );
insert into employee (id,department_id,employee,address) values ( 10,8,'Christy Moynihan','Meath' );
insert into employee (id,department_id,employee,address) values ( 11,2,'Niall Lane','Dublin' );
insert into employee (id,department_id,employee,address) values ( 12,5,'Bryson Teller','Offally');




insert into user (email,name,password,role) values ( 'admin@management.com', 'Brendan Paxton', 'password', 'admin' );
insert into user (email,name,password,role) values ( 'employee@management.com', 'Ed Employe', 'password', 'employee' );

