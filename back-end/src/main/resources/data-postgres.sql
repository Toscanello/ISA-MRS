insert into dpharmacy (name) values ('Apoteka Jankovic');
insert into dpharmacy (name) values ('Apoteka Betty');

insert into location (width, height) values (0.0, 0.0);
insert into location (width, height) values (1.0, 1.0);

insert into address (street, street_number, place, country) values ('Bulevar Despota Stefana', '7', 'Novi Sad', 'Srbija');

insert into address_location(location_id, address_id) values (1,1);

insert into pharmacy_address(address_id, pharmacy_id) values (1,1);