select * from vehicle as v join vehicle_type as vt on v.vehicle_type_id = vt.id;
select v.name, vt.name from vehicle as v join vehicle_type as vt on v.vehicle_type_id = vt.id;
select v.name as "Транспортное средство", vt.name as "Тип ТС", v.date_of_birth as "Дата выпуска" from vehicle v join vehicle_type vt on v.vehicle_type_id = vt.id;
select v.name ТС, vt.name Тип, v.date_of_birth as "Дата выпуска" from vehicle v join vehicle_type vt on v.vehicle_type_id = vt.id;