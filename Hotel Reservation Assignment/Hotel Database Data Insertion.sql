use HotelReservation;

insert into customer (customerName, customerPhone, customerEmail)
values ('John Smith', '555-555-5555', 'JS@gmail.com'),
	('Jane Doe', '556-556-5556', 'JD@live.com');
    
insert into guestlist (nameofguests, numofguests, agesofguests)
values ('John, Josh, Brian', '3', '30, 10, 12');
    
insert into addon (addonitems)
value ('Movie: Die Hard, Room Service: Chicken Dinner');

insert into amenity (amenitynames)
value ('Spa Bath, 60" Screen TV');
    
insert into rate (startdate, enddate, price)
value ('2018/04/10', '2018/04/12', '100.00');

insert into roomtype (roomtypename)
value ('double');

insert into room (roomtypeid, roomnumber, floornumber, occupancylimit)
value ('1', '20', '2', '5');

insert into reservation (customerid, guestlistid, startdate, enddate)
value ('1', '1', '2018/04/11', '2018/04/12');


    
    
    
    
    
 