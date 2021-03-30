insert into pharmacy (reg_no, name) values ('abc', 'Apoteka Jankovic');
--insert into dpharmacy (name) values ('Apoteka Betty');

insert into location (width, height) values (45.0, 20.0);

insert into address (street, street_number, place, country) values ('Dimitrija Tucovica', '7', 'Uzice', 'Srbija');

insert into address_location(location_id, address_id) values (1, 1);

insert into pharmacy_address(address_id, pharmacy_reg_no) values (1, 'abc');

insert into medicine(name, code, type, manufacturer, drug_form, composition, description, category)
values ('Remdesivir', 'lek1', 'tip', 'fajzer', 'sirup', 'sastav', 'opis', 'POM');

insert into medicine(name, code, type, manufacturer, drug_form, composition, description, category)
values ('Bensedin', 'lek2', 'tip', 'galenika', 'kapsula', 'sastav', 'opis', 'POM');

insert into medicine(name, code, type, manufacturer, drug_form, composition, description, category)
values ('Herbiko', 'lek3', 'tip', 'zdravko', 'tableta', 'sastav', 'opis', 'P');

insert into medicine(name, code, type, manufacturer, drug_form, composition, description, category)
values ('Probio Forte', 'lek4', 'tip', 'galenika', 'kapsula', 'sastav', 'opis', 'P');


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