/*ROLES*/
insert into role (role_id, role) values (1, 'ADMIN');
insert into role(role_id, role) values (2, 'USER');

/*users*/
insert into user(id, active, name, password )
value(1,1,'ADMIN', '$2a$10$riiPd4fYYUX53BQrVNqb2O6DZb4BlZ3CnBmILQh7IYHvBOEZ4bpJO');

insert into user(id, active, name, password )
value(2,1,'USER', '$2a$10$Oh4041rI3Tc9RsL9WEfpI.1spuqzMO.1Yo5jMJuY0GG5b6B6ZQx1u');

/*ADD ROLE TO USER*/
insert into user_role (user_id, role_id) values (1,1);
insert into user_role (user_id, role_id) values (1,2);
insert into user_role (user_id, role_id) values (2,2);