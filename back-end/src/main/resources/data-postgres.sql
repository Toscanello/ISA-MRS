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
--insert into medicine_quantity(medicine_code, quantity)
--values ('lek1', 2);
--insert into medicine_quantity(medicine_code, quantity)
--values ('lek2', 3);
--insert into medicine_quantity(medicine_code, quantity)
--values ('lek3', 3);
--insert into medicine_quantity(medicine_code, quantity)
--values ('lek4', 4);

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
values('c@gmail.com','pass','mika','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('d@gmail.com','pass','mika','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');

insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('derm1@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','derm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('derm2@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','drmtlg','matovic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('farm@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('farmA@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');
insert into users(email,password,name,surname,phone_number,address_id, enabled, last_password_reset_date)
values('farmB@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','farm','luburic','+38154784566',1, true, '2017-10-01 21:58:58.508-07');

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO ROLE (name) VALUES ('ROLE_DERMATOLOGIST');
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

insert into dermatologist(email)values('derm1@gmail.com');
insert into dermatologist(email)values('derm2@gmail.com');

insert into pharmacy_dermatologists(pharmacy_reg_no, dermatologist_email)
values ('abc', 'derm1@gmail.com');
insert into pharmacy_dermatologists(pharmacy_reg_no, dermatologist_email)
values ('abc', 'derm2@gmail.com');

insert into d_work_hour(dermatologist_email, pharmacy_reg_no, beggining_hour, ending_hour)
values ('derm1@gmail.com', 'abc', '09:00', '17:00');
insert into d_work_hour(dermatologist_email, pharmacy_reg_no, beggining_hour, ending_hour)
values ('derm2@gmail.com', 'abc', '17:00', '23:00');

insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled)
values('ika@gmail.com','derm1@gmail.com',1000, '2021-04-06 09:15:00', '2021-04-06 09:45:00', false);
insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled)
values('ika@gmail.com','derm1@gmail.com',1000, '2021-04-30 09:15:00', '2021-04-30 09:45:00', false);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled)
values('pera@gmail.com','derm2@gmail.com',1000, '2021-04-06 19:15:00', '2021-04-06 19:45:00', false);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled)
values('mika95455@gmail.com','farm@gmail.com',1000, '2021-04-06 09:15:00', '2021-04-06 09:45:00', false);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled)
values('mika95455@gmail.com','farm@gmail.com',1000, '2021-04-27 18:15:00', '2021-04-27 18:45:00', false);
insert into appointment(patient_id,medical_worker_id, price, start_time, end_time, canceled)
values('a@gmail.com','derm1@gmail.com',1000, '2021-04-06 10:15:00', '2021-04-06 10:45:00', false);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled)
values('b@gmail.com','derm1@gmail.com',1000, '2021-04-06 11:15:00', '2021-04-06 11:45:00', false);
insert into appointment(patient_id,medical_worker_id,price, start_time, end_time, canceled)
values('c@gmail.com','derm1@gmail.com',1000, '2021-04-06 12:15:00', '2021-04-06 12:45:00', false);


insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('abc', 'lek1', 2);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('abc', 'lek2', 3);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('abc', 'lek4', 1);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('aaa', 'lek3', 4);
insert into medicine_quantity(pharmacy_reg_no, medicine_code, quantity)
values ('aaa', 'lek2', 4);

insert into medicine_pricing(medicine_code, pharmacy_reg_no, price, pricing_start)
values('lek1', 'abc', 300, '2021-04-21 12:00:00');
insert into medicine_pricing(medicine_code, pharmacy_reg_no, price, pricing_start)
values('lek2', 'abc', 320, '2021-04-21 13:00:00');
insert into medicine_pricing(medicine_code, pharmacy_reg_no, price, pricing_start)
values('lek4', 'abc', 320, '2021-04-21 13:00:00');
--insert into appointment(patient_id,medical_worker_id,price, startTime, endTime)values('pera@gmail.com','derm@gmail.com',1000, "2011-10-30 23:59:30", "2011-10-30 23:59:30");
--insert into appointment(patient_id,medical_worker_id,price, startTime, endTime)values('mika@gmail.com','farm@gmail.com',1000, "2011-10-30 23:59:30", "2011-