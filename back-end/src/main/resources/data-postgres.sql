--insert into dpharmacy (name) values ('Apoteka Betty');

insert into location (width, height) values (19.85, 45.26);
insert into location (width, height) values (25.85, 45.26);
insert into address (street, street_number, place, country,location_id) values ('Dimitrija Tucovica', '7', 'Uzice', 'Srbija',1);
insert into address (street, street_number, place, country,location_id) values ('Bulevar Jase Tomica', '29', 'Novi Sad', 'Srbija',2);
insert into address (street, street_number, place, country,location_id) values ('Bulevar Despota Stefana', '7', 'Novi Sad', 'Srbija',1);
insert into address (street, street_number, place, country,location_id) values ('Bulevar Evrope', '3', 'Novi Sad', 'Srbija',2);
insert into address (street, street_number, place, country,location_id) values ('Brace Ribnikar', '7', 'Novi Sad', 'Srbija',1);

insert into pharmacy (reg_no, name, address_id, appointment_price, version) values ('abc', 'Apoteka Jankovic', 1, 1000.00, 0);
insert into pharmacy (reg_no, name, address_id, appointment_price, version) values ('aaa', 'Apoteka Betty', 2, 2000.00, 0);
insert into pharmacy (reg_no, name, address_id, appointment_price, version) values ('bbb', 'Apoteka B', 3, 3000.00, 0);
insert into pharmacy (reg_no, name, address_id, appointment_price, version) values ('ccc', 'Apoteka C', 4, 4000.00, 0);
insert into pharmacy (reg_no, name, address_id, appointment_price, version) values ('ddd', 'Apoteka D', 5, 5000.00, 0);
--insert into pharmacy (reg_no, name, address_id, appointment_price) values ('eee', 'Apoteka E', 2, 6000.00);
--insert into pharmacy (reg_no, name, address_id, appointment_price) values ('fff', 'Apoteka F', 1, 7000.00);
--insert into pharmacy (reg_no, name, address_id, appointment_price) values ('ggg', 'Apoteka G', 2, 8000.00);
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

insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity, version)
values ('abc', 'lek1', 4, 0);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity, version)
values ('abc', 'lek2', 3, 0);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity, version)
values ('abc', 'lek3', 1, 0);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity, version)
values ('aaa', 'lek4', 4, 0);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity, version)
values ('aaa', 'lek2', 4, 0);

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

insert into users(email,password,name,surname,phone_number,address_id,enabled, last_password_reset_date, version)
values('ika@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','ivan','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('pera@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','pera','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('mika95455@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','mika','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('a@gmail.com','pass','a','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('b@gmail.com','pass','b','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('c@gmail.com','pass','c','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('d@gmail.com','pass','d','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);

insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('derm1@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','derm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('derm2@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','drmtlg','matovic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('farm@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('farmA@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('farmB@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('farmC@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','temp','farm','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);

insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('phadmin@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','admin','apotekic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date, version)
values('phadmin1@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','admin','apotekic','+38154784566',1, true, '2017-10-01 21:58:58.508-07', 0);

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
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('phadmin1@gmail.com', 4);

insert into patients(email,penalty)values('ika@gmail.com',4);
insert into patients(email,penalty)values('pera@gmail.com',0);
insert into patients(email,penalty)values('mika95455@gmail.com',0);
insert into patients(email,penalty)values('a@gmail.com',0);
insert into patients(email,penalty)values('b@gmail.com',0);
insert into patients(email,penalty)values('c@gmail.com',0);
insert into patients(email,penalty)values('d@gmail.com',0);

insert into medical_workers(email)values('derm1@gmail.com');
insert into medical_workers(email)values('derm2@gmail.com');
insert into medical_workers(email)values('farm@gmail.com');
insert into medical_workers(email)values('farmA@gmail.com');
insert into medical_workers(email)values('farmB@gmail.com');
insert into medical_workers(email)values('farmC@gmail.com');

insert into pharmacy_admin(email, pharmacy_reg_no)
values ('phadmin@gmail.com', 'abc');
insert into pharmacy_admin(email, pharmacy_reg_no)
values ('phadmin1@gmail.com', 'ccc');

insert into work_hour(start_time,end_time,pharmacy)
values('09:00','17:00','abc');
insert into work_hour(start_time,end_time,pharmacy)
values('09:00','17:00','bbb');
insert into work_hour(start_time,end_time,pharmacy)
values('09:00','17:00','ccc');
insert into work_hour(start_time,end_time,pharmacy)
values('09:00','17:00','abc');

insert into pharmacist(email,work_hour,pharmacy)
values('farm@gmail.com',1,'abc');
insert into pharmacist(email,work_hour,pharmacy)
values('farmA@gmail.com',2,'bbb');
insert into pharmacist(email,work_hour,pharmacy)
values('farmB@gmail.com',3,'ccc');
insert into pharmacist(email,work_hour,pharmacy)
values('farmC@gmail.com',4,'abc');

insert into dermatologist(email) values('derm1@gmail.com');
insert into dermatologist(email) values('derm2@gmail.com');

insert into pharmacy_dermatologists(pharmacy_reg_no, dermatologist_email)
values ('abc', 'derm1@gmail.com');
insert into pharmacy_dermatologists(pharmacy_reg_no, dermatologist_email)
values ('ccc', 'derm1@gmail.com');
insert into pharmacy_dermatologists(pharmacy_reg_no, dermatologist_email)
values ('abc', 'derm2@gmail.com');

insert into d_work_hour(dermatologist_email, pharmacy_reg_no, beggining_hour, ending_hour)
values ('derm1@gmail.com', 'abc', '09:00', '17:00');
insert into d_work_hour(dermatologist_email, pharmacy_reg_no, beggining_hour, ending_hour)
values ('derm1@gmail.com', 'ccc', '17:30', '19:30');
insert into d_work_hour(dermatologist_email, pharmacy_reg_no, beggining_hour, ending_hour)
values ('derm2@gmail.com', 'abc', '17:00', '23:00');

insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('ika@gmail.com','derm1@gmail.com',1000, '2021-06-12 16:30:00', '2021-06-12 16:45:00', false,'abc',false,true);
insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('ika@gmail.com','farm@gmail.com',1000, '2021-06-14 18:10:00', '2021-06-14 18:15:00', false,'abc',false,true);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('pera@gmail.com','derm2@gmail.com',1000, '2021-04-06 19:15:00', '2021-04-06 19:45:00', false,'abc',true,true);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('mika95455@gmail.com','farm@gmail.com',1000, '2021-05-26 11:45:00', '2021-05-26 12:15:00', false,'abc',true,true);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('mika95455@gmail.com','farm@gmail.com',1000, '2021-04-28 10:15:00', '2021-04-28 10:45:00', false,'abc',true,true);
insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('a@gmail.com','derm1@gmail.com',1000, '2021-04-06 10:15:00', '2021-04-06 10:45:00', false,'abc',true,true);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('b@gmail.com','derm1@gmail.com',1000, '2021-04-06 11:15:00', '2021-04-06 11:45:00', false,'abc',true,true);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('c@gmail.com','derm1@gmail.com',1000, '2021-04-06 18:15:00', '2021-04-06 18:45:00', false,'ccc',true,true);
insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled, pharmacy_reg_no,finished,appearance)
values('mika95455@gmail.com','derm1@gmail.com',1000, '2021-06-12 12:55:00', '2021-06-12 13:10:00', false,'ccc',false,false);

insert into dermatologist_appointment(time,duration,dermatologist_email,pharmacy_reg_no,price)
values('2021-07-07 14:15:00','14:45:00','derm1@gmail.com','abc',1000);
insert into dermatologist_appointment(time,duration,dermatologist_email,pharmacy_reg_no,price)
values('2021-06-08 14:15:00','14:45:00','derm1@gmail.com','abc',1000);
insert into dermatologist_appointment(time,duration,dermatologist_email,pharmacy_reg_no,price)
values('2021-07-09 14:15:00','14:45:00','derm1@gmail.com','abc',1000);
insert into dermatologist_appointment(time,duration,dermatologist_email,pharmacy_reg_no,price)
values('2021-06-10 14:15:00','14:45:00','derm1@gmail.com','abc',1000);



insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farm@gmail.com','2021-04-09','2021-04-18','ACCEPT', 0);
insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farm@gmail.com','2021-05-09','2021-05-18','STANDBY', 0);
insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farm@gmail.com','2021-03-09','2021-03-18','DECLINE', 0);
insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farmC@gmail.com','2021-04-09','2021-04-18','ACCEPT', 0);
insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farmC@gmail.com','2021-05-09','2021-05-18','STANDBY', 0);
insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farmC@gmail.com','2021-03-09','2021-03-18','DECLINE', 0);
insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farmB@gmail.com','2021-04-09','2021-04-18','ACCEPT', 0);
insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farmB@gmail.com','2021-05-09','2021-05-18','STANDBY', 0);
insert into vaccation(medical_worker_id,start_date,end_date,status, version)
values('farmB@gmail.com','2021-03-09','2021-03-18','DECLINE', 0);

insert into medicine_order(medicine, quantity, price, patient_id, start_time, end_time,status) values (1, 2, 600,
                                            'ika@gmail.com', '2021-04-21 12:00:00', '2021-04-21 12:00:00',true);
insert into medicine_order(medicine, quantity, price, patient_id, start_time, end_time,status) values (1, 2, 600,
                                            'ika@gmail.com', '2021-04-21 12:00:00', '2021-04-21 12:00:00',true);
insert into medicine_order(medicine, quantity, price, patient_id, start_time, end_time,status) values (1, 3, 900,
                                            'ika@gmail.com', '2021-04-21 12:00:00', '2021-05-16 12:00:00',false);
insert into medicine_order(medicine, quantity, price, patient_id, start_time, end_time,status) values (2, 3, 960,
                                            'ika@gmail.com', '2021-04-21 12:00:00', '2021-05-29 12:00:00',true);


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

insert into order_response(order_id, price, supplier, available_date, status, version)
values(1, 2500, 'Klisura', '2021-06-10', 'PENDING', 0);
insert into order_response(order_id, price, supplier, available_date, status, version)
values(1, 2600, 'Simuna', '2021-06-11', 'PENDING', 0);
insert into order_response(order_id, price, supplier, available_date, status, version)
values(1, 2300, 'Zbrda Zdola', '2021-06-12', 'PENDING', 0);
insert into order_response(order_id, price, supplier, available_date, status, version)
values(2, 2000, 'Skonca i skonopca', '2021-06-10', 'PENDING', 0);
insert into order_response(order_id, price, supplier, available_date, status, version)
values(2, 1500, 'Ovo Ono', '2021-06-14', 'PENDING', 0);

insert into patient_pharmacies(patient_email, pharmacy_reg_no) values ('ika@gmail.com', 'abc');
insert into patient_pharmacies(patient_email, pharmacy_reg_no) values ('mika95455@gmail.com', 'abc');
insert into patient_pharmacies(patient_email, pharmacy_reg_no) values ('mika95455@gmail.com', 'ccc');
insert into patient_pharmacies(patient_email, pharmacy_reg_no) values ('pera@gmail.com', 'ccc');

insert into pharmacist_rating(patient, pharmacist, rating) values('ika@gmail.com', 'farm@gmail.com', 2.5);
insert into pharmacist_rating(patient, pharmacist, rating) values('mika95455@gmail.com', 'farm@gmail.com', 4.5);
insert into pharmacist_rating(patient, pharmacist, rating) values('pera@gmail.com', 'farm@gmail.com', 5);

insert into pharmacist_rating(patient, pharmacist, rating) values('ika@gmail.com', 'farmC@gmail.com', 5);
insert into pharmacist_rating(patient, pharmacist, rating) values('mika95455@gmail.com', 'farmC@gmail.com', 4.5);
insert into pharmacist_rating(patient, pharmacist, rating) values('pera@gmail.com', 'farmC@gmail.com', 5);


insert into dermatologist_rating(patient, dermatologist, rating) values('ika@gmail.com', 'derm1@gmail.com', 4);
insert into dermatologist_rating(patient, dermatologist, rating) values('mika95455@gmail.com', 'derm1@gmail.com', 3);
insert into dermatologist_rating(patient, dermatologist, rating) values('pera@gmail.com', 'derm1@gmail.com', 5);

insert into dermatologist_rating(patient, dermatologist, rating) values('ika@gmail.com', 'derm2@gmail.com', 3.5);
insert into dermatologist_rating(patient, dermatologist, rating) values('mika95455@gmail.com', 'derm2@gmail.com', 2.5);
insert into dermatologist_rating(patient, dermatologist, rating) values('pera@gmail.com', 'derm2@gmail.com', 5);


insert into patient_allergies(patient_id,medicine_code) values('ika@gmail.com','lek1');