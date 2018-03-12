use HotelReservation;

create table Customer (
	CustomerID int not null auto_increment,
	CustomerName varchar(30) not null,
    CustomerPhone varchar(10) not null,
    CustomerEmail varchar(40) not null,
    primary key (CustomerID)
);

create table GuestList (
	GuestListID int not null auto_increment,
	NumOfGuests int null,
	NameOfGuests varchar(200) null,
    AgesOfGuests varchar(25) null,
    primary key (GuestListID)
);

create table PromoCode (
	PromoCodeID int not null auto_increment,
    PercentOfDiscount int null,
    FlatRateDiscount int null,
    primary key (PromoCodeID)
);

create table Rate (
	RateID int not null auto_increment,
    StartDate date,
    EndDate date,
    Price decimal(5,2),
    primary key (RateID)
);

create table RoomType (
	RoomTypeID int not null auto_increment,
    RoomTypeName varchar(15),
    primary key (RoomTypeID)
);

create table RoomTypeRate (
	RateID int not null,
    RoomTypeID int not null,
    primary key (RateID, RoomTypeID),
	foreign key (RateID) references Rate(RateID),
    foreign key (RoomTypeID) references RoomType(RoomTypeID)
);

create table Room (
	RoomID int not null auto_increment,
    RoomTypeID int not null,
    RoomNumber int not null,
    FloorNumber int not null,
    OccupancyLimit int not null,
    primary key (RoomID),
    foreign key (RoomTypeID) references RoomType(RoomTypeID)
);

create table Amenity (
	AmenityID int not null auto_increment,
    AmenityNames varchar(100),
    primary key (AmenityID)
);

create table RoomAmenity (
	RoomID int not null,
    AmenityID int not null,
    primary key (RoomID, AmenityID),
	foreign key (RoomID) references Room(RoomID),
    foreign key (AmenityID) references Amenity(AmenityID)
);

create table AddOn (
	AddOnID int not null auto_increment,
    AddOnItems varchar(100),
    primary key (AddOnID)
);

create table Reservation (
	ReservationID int not null auto_increment,
    CustomerID int not null,
    GuestListID int null,
    PromoCodeID int null,
    StartDate date,
    EndDate date,
    primary key (ReservationID),
    foreign key (CustomerID) references Customer(CustomerID),
    foreign key (GuestListID) references GuestList(GuestListID),
    foreign key (PromoCodeID) references PromoCode(PromoCodeID)
);

create table ReservationAddOn (
	AddOnID int not null,
    ReservationID int not null,
    primary key (AddOnID, ReservationID),
	foreign key (AddOnID) references AddOn(AddOnID),
    foreign key (ReservationID) references Reservation(ReservationID)
);

create table ReservationRoom (
	RoomID int not null,
    ReservationID int not null,
    primary key (RoomID, ReservationID),
	foreign key (RoomID) references Room(RoomID),
    foreign key (ReservationID) references Reservation(ReservationID)
);







