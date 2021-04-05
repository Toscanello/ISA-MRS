--insert into dpharmacy (name) values ('Apoteka Betty');

insert into location (width, height) values (19.85, 45.26);
insert into address (street, street_number, place, country,location_id) values ('Dimitrija Tucovica', '7', 'Uzice', 'Srbija',1);
insert into address (street, street_number, place, country,location_id) values ('Bulevar Jase Tomica', '29', 'Novi sad', 'Srbija',1);
insert into pharmacy (reg_no, name, address_id) values ('abc', 'Apoteka Jankovic',1);
insert into pharmacy (reg_no, name, address_id) values ('aaa', 'Apoteka Betty',1);
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

insert into users(email,password,name,surname,phone_number,address_id)
values('ika@gmail.com','pass','ivan','luburic','+38154784566',1);
insert into users(email,password,name,surname,phone_number,address_id)
values('pera@gmail.com','pass','pera','luburic','+38154784566',1);
insert into users(email,password,name,surname,phone_number,address_id)
values('mika@gmail.com','pass','mika','luburic','+38154784566',1);
insert into users(email,password,name,surname,phone_number,address_id)
values('a@gmail.com','pass','mika','luburic','+38154784566',1);
insert into users(email,password,name,surname,phone_number,address_id)
values('b@gmail.com','pass','mika','luburic','+38154784566',1);
insert into users(email,password,name,surname,phone_number,address_id)
values('c@gmail.com','pass','mika','luburic','+38154784566',1);
insert into users(email,password,name,surname,phone_number,address_id)
values('d@gmail.com','pass','mika','luburic','+38154784566',1);

insert into users(email,password,name,surname,phone_number,address_id)
values('derm@gmail.com','pass','derm','luburic','+38154784566',1);
insert into users(email,password,name,surname,phone_number,address_id)
values('farm@gmail.com','pass','farm','luburic','+38154784566',1);

insert into patients(email)values('ika@gmail.com');
insert into patients(email)values('pera@gmail.com');
insert into patients(email)values('mika@gmail.com');
insert into patients(email)values('a@gmail.com');
insert into patients(email)values('b@gmail.com');
insert into patients(email)values('c@gmail.com');
insert into patients(email)values('d@gmail.com');

insert into medical_workers(email)values('derm@gmail.com');
insert into medical_workers(email)values('farm@gmail.com');

insert into work_hour(start_time, end_time, pharmacy) values ('07:00:00','11:00:00', 'abc');
insert into pharmacist(email, work_hour, pharmacists_pharmacy) values ('farm@gmail.com', 1, 'abc');

insert into work_hour(start_time, end_time, pharmacy) values ('07:00:00','07:15:00', 'aaa');
insert into pharmacist(email, work_hour, pharmacists_pharmacy) values ('derm@gmail.com', 2, 'aaa');

insert into appointment(patient_id, medical_worker_id, price, start_time, end_time)values('ika@gmail.com','farm@gmail.com',1000, '2019-05-04 09:00:00', '2019-05-04 09:25:00');
--insert into appointment(patient_id,medical_worker_id,price, startTime, endTime)values('pera@gmail.com','derm@gmail.com',1000, "2011-10-30 23:59:30", "2011-10-30 23:59:30");
--insert into appointment(patient_id,medical_worker_id,price, startTime, endTime)values('mika@gmail.com','farm@gmail.com',1000, "2011-10-30 23:59:30", "2011-