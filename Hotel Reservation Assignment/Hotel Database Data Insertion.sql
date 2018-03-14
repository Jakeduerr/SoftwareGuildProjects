use HotelReservation;

insert into customer (`Name`, Phone, Email)
values ('John Smith', '5555555555', 'JS@gmail.com'),
	('Jane Doe', '5565565556', 'JD@live.com');

insert into addon (addonitem)
value ('Movie: Die Hard');

insert into amenity (`name`)
value ('Spa Bath');
    
insert into rate (startdate, enddate, price)
value ('2018/04/10', '2018/04/12', '100.00');

insert into roomtype (roomtypename)
value ('double');

insert into room (roomtypeid, roomnumber, floornumber, occupancylimit)
value ('1', '20', '2', '5');



    
    
    
    
    
 