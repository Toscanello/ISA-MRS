--insert into dpharmacy (name) values ('Apoteka Betty');

insert into location (width, height) values (19.85, 45.26);
insert into location (width, height) values (25.85, 45.26);
insert into address (street, street_number, place, country,location_id) values ('Dimitrija Tucovica', '7', 'Uzice', 'Srbija',1);
insert into address (street, street_number, place, country,location_id) values ('Bulevar Jase Tomica', '29', 'Novi sad', 'Srbija',2);
insert into pharmacy (reg_no, name, address_id, appointment_price) values ('abc', 'Apoteka Jankovic', 1, 1000.00);
insert into pharmacy (reg_no, name, address_id, appointment_price) values ('aaa', 'Apoteka Betty', 2, 2000.00);
insert into pharmacy (reg_no, name, address_id, appointment_price) values ('bbb', 'Apoteka B', 1, 3000.00);
insert into pharmacy (reg_no, name, address_id, appointment_price) values ('ccc', 'Apoteka C', 2, 4000.00);
insert into pharmacy (reg_no, name, address_id, appointment_price) values ('ddd', 'Apoteka D', 1, 5000.00);
insert into pharmacy (reg_no, name, address_id, appointment_price) values ('eee', 'Apoteka E', 2, 6000.00);
insert into pharmacy (reg_no, name, address_id, appointment_price) values ('fff', 'Apoteka F', 1, 7000.00);
insert into pharmacy (reg_no, name, address_id, appointment_price) values ('ggg', 'Apoteka G', 2, 8000.00);
--insert into pharmacy_address(address_id, pharmacy_reg_no) values (1, 'abc');

insert into medicine(name, code, type, manufacturer, drug_form, composition, description, category)
values ('Remdesivir', 'lek1', 'tip', 'fajzer', 'sirup', 'sastav', 'opis', 'POM');
insert into medicine(name, code, type, manufacturer, drug_form, composition, description, category)
values ('Bensedin', 'lek2', 'tip', 'galenika', 'kapsula', 'sastav', 'opis', 'POM');
insert into medicine(name, code, type, manufacturer, drug_form, composition, description, category)
values ('Herbiko', 'lek3', 'tip', 'zdravko', 'tableta', 'sastav', 'opis', 'P');
insert into medicine(name, code, type, manufacturer, drug_form, composition, description, category)
values ('Probio Forte', 'lek4', 'tip', 'galenika', 'kapsula', 'sastav', 'opis', 'P');

insert into rating(pharmacy_reg_no, rating) values ('abc', 5);
insert into rating(pharmacy_reg_no, rating) values ('abc', 4);
insert into rating(pharmacy_reg_no, rating) values ('aaa', 4);
insert into rating(pharmacy_reg_no, rating) values ('bbb', 5);
insert into rating(pharmacy_reg_no, rating) values ('ccc', 2);

insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('abc', 'lek1', 2);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('abc', 'lek2', 3);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('abc', 'lek3', 1);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('aaa', 'lek4', 4);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('aaa', 'lek2', 4);

insert into medicine_pricing(medicine_code, pharmacy_reg_no, price, pricing_start)
values('lek1', 'abc', 300, '2021-04-21 12:00:00');
insert into medicine_pricing(medicine_code, pharmacy_reg_no, price, pricing_start)
values('lek2', 'abc', 320, '2021-04-21 13:00:00');
insert into medicine_pricing(medicine_code, pharmacy_reg_no, price, pricing_start)
values('lek3', 'abc', 320, '2021-04-21 13:00:00');
insert into medicine_pricing(medicine_code, pharmacy_reg_no, price, pricing_start)
values('lek4', 'aaa', 320, '2021-04-21 13:00:00');

--insert into pharmacy_medicine(pharmacy_reg_no, medicine_code)
--values ('abc', 'lek1');
--insert into pharmacy_medicine(pharmacy_reg_no, medicine_code)
--values ('abc', 'lek2');
--insert into pharmacy_medicine(pharmacy_reg_no, medicine_code)
--values ('abc', 'lek3');
--insert into pharmacy_medicine(pharmacy_reg_no, medicine_code)
--values ('abc', 'lek4');

insert into alternative_medicine(medicine_code, alternative_code)
values('lek3', 'lek4');
insert into alternative_medicine(medicine_code, alternative_code)
values('lek4', 'lek3');

insert into users(email,password,name,surname,phone_number,address_id,enabled, last_password_reset_date)
values('ika@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','ivan','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('pera@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','pera','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('mika95455@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','mika','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('a@gmail.com','pass','a','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('b@gmail.com','pass','b','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('c@gmail.com','pass','c','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('d@gmail.com','pass','d','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');

insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('derm1@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','derm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('derm2@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','drmtlg','matovic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('farm@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('farmA@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('farmB@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');;
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('farmC@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','proba','probic prvi','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('farmD@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','proba','probic drugi','+38154784566',1, true, '2017-10-01 21:58:58.508-07');

insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('phadmin@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','admin','apotekic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO ROLE (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO ROLE (name) VALUES ('ROLE_PH_ADMIN');

INSERT INTO USER_ROLE (user_id, role_id) VALUES ('ika@gmail.com', 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('pera@gmail.com', 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('mika95455@gmail.com', 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('a@gmail.com', 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('b@gmail.com', 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('c@gmail.com', 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('d@gmail.com', 1);

INSERT INTO USER_ROLE (user_id, role_id) VALUES ('derm1@gmail.com', 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('derm2@gmail.com', 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('farm@gmail.com', 2);

INSERT INTO USER_ROLE (user_id, role_id) VALUES ('phadmin@gmail.com', 4);

insert into patients(email)values('ika@gmail.com');
insert into patients(email)values('pera@gmail.com');
insert into patients(email)values('mika95455@gmail.com');
insert into patients(email)values('a@gmail.com');
insert into patients(email)values('b@gmail.com');
insert into patients(email)values('c@gmail.com');
insert into patients(email)values('d@gmail.com');

insert into medical_workers(email)values('derm1@gmail.com');
insert into medical_workers(email)values('derm2@gmail.com');
insert into medical_workers(email)values('farm@gmail.com');
insert into medical_workers(email)values('farmA@gmail.com');
insert into medical_workers(email)values('farmB@gmail.com');
insert into medical_workers(email)values('farmC@gmail.com');
insert into medical_workers(email)values('farmD@gmail.com');

insert into pharmacy_admin(email, pharmacy_reg_no)
values ('phadmin@gmail.com', 'abc');

insert into work_hour(start_time,end_time,pharmacy)
values('09:00','17:00','abc');
insert into work_hour(start_time,end_time,pharmacy)
values('09:00','17:00','bbb');
insert into work_hour(start_time,end_time,pharmacy)
values('09:00','17:00','ccc');
insert into pharmacist(email,work_hour,pharmacy)
values('farm@gmail.com',1,'abc');
insert into pharmacist(email,work_hour,pharmacy)
values('farmA@gmail.com',2,'bbb');
insert into pharmacist(email,work_hour,pharmacy)
values('farmB@gmail.com',3,'ccc');
insert into pharmacist(email,work_hour,pharmacy)
values('farmC@gmail.com', null, null);
insert into pharmacist(email,work_hour,pharmacy)
values('farmD@gmail.com', null, null);

insert into dermatologist(email)values('derm1@gmail.com');
insert into dermatologist(email)values('derm2@gmail.com');

insert into pharmacy_dermatologists(pharmacy_reg_no, dermatologist_email)
values ('abc', 'derm1@gmail.com');
insert into pharmacy_dermatologists(pharmacy_reg_no, dermatologist_email)
values ('abc', 'derm2@gmail.com');

insert into d_work_hour(dermatologist_email, pharmacy_reg_no, beggining_hour, ending_hour)
values ('derm1@gmail.com', 'abc', '09:00', '17:00');
insert into d_work_hour(dermatologist_email, pharmacy_reg_no, beggining_hour, ending_hour)
values ('derm1@gmail.com', 'ccc', '17:30', '19:30');
insert into d_work_hour(dermatologist_email, pharmacy_reg_no, beggining_hour, ending_hour)
values ('derm2@gmail.com', 'abc', '17:00', '23:00');

insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled, pharmacy_reg_no)
values('ika@gmail.com','derm1@gmail.com',1000, '2021-04-06 09:15:00', '2021-04-06 09:45:00', false,'abc');
insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled, pharmacy_reg_no)
values('ika@gmail.com','farm@gmail.com',1000, '2021-04-29 09:15:00', '2021-04-29 09:45:00', false,'abc');
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no)
values('pera@gmail.com','derm2@gmail.com',1000, '2021-05-06 19:15:00', '2021-04-06 19:45:00', false,'abc');
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no)
values('mika95455@gmail.com','farm@gmail.com',1000, '2021-05-26 11:45:00', '2021-05-26 12:15:00', false,'abc');
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no)
values('mika95455@gmail.com','farm@gmail.com',1000, '2021-04-28 10:15:00', '2021-04-28 10:45:00', false,'abc');
insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled, pharmacy_reg_no)
values('a@gmail.com','derm1@gmail.com',1000, '2021-06-06 10:15:00', '2021-04-06 10:45:00', false,'abc');
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no)
values('b@gmail.com','derm1@gmail.com',1000, '2021-06-06 11:15:00', '2021-04-06 11:45:00', false,'abc');
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no)
values('c@gmail.com','derm1@gmail.com',1000, '2021-07-06 18:15:00', '2021-04-06 18:45:00', false,'ccc');

insert into dermatologist_appointment(time,duration,dermatologist_email,pharmacy_reg_no,price)
values('2021-05-21 09:15:00','00:15:00','derm1@gmail.com','abc',1000);


insert into vaccation(medical_worker_id,start_date,end_date,status)
values('farm@gmail.com','2021-05-09','2021-05-18','STANDBY');

insert into medicine_order(medicine, quantity, price, patient_id, start_time, end_time,status) values (1, 2, 600,
                                            'ika@gmail.com', '2021-04-21 12:00:00', '2021-04-21 12:00:00',false);
insert into medicine_order(medicine, quantity, price, patient_id, start_time, end_time,status) values (1, 3, 900,
                                            'ika@gmail.com', '2021-04-21 12:00:00', '2021-05-16 12:00:00',false);
insert into medicine_order(medicine, quantity, price, patient_id, start_time, end_time,status) values (2, 3, 960,
                                            'ika@gmail.com', '2021-07-21 12:00:00', '2021-07-29 12:00:00',false);


insert into bulk_order(due_date, pharmacy_reg_no)
values ('2021-05-01', 'abc');
insert into bulk_order(due_date, pharmacy_reg_no)
values ('2021-05-03', 'abc');

insert into bulk_order_item(medicine_code, order_id, amount)
values('lek1', 1, 3);
insert into bulk_order_item(medicine_code, order_id, amount)
values('lek2', 1, 4);
insert into bulk_order_item(medicine_code, order_id, amount)
values('lek4', 1, 3);

insert into bulk_order_item(medicine_code, order_id, amount)
values('lek2', 2, 3);
insert into bulk_order_item(medicine_code, order_id, amount)
values('lek3', 2, 3);
insert into bulk_order_item(medicine_code, order_id, amount)
values('lek4', 2, 3);

insert into order_response(order_id, price, supplier, available_date, status)
values(1, 2500, 'Klisura', '2021-06-10', 'PENDING');
insert into order_response(order_id, price, supplier, available_date, status)
values(1, 2600, 'Simuna', '2021-06-11', 'PENDING');
insert into order_response(order_id, price, supplier, available_date, status)
values(1, 2300, 'Zbrda Zdola', '2021-06-12', 'PENDING');
insert into order_response(order_id, price, supplier, available_date, status)
values(2, 2000, 'Skonca i skonopca', '2021-06-10', 'PENDING');
insert into order_response(order_id, price, supplier, available_date, status)
values(2, 1500, 'Ovo Ono', '2021-06-14', 'PENDING');