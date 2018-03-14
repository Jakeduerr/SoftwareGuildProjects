drop database if exists HotelReservation;

create database HotelReservation;

use HotelReservation;

create table Customer (
	CustomerID int not null auto_increment,
	`Name` varchar(50) not null,
    Phone varchar(15) not null,
    Email varchar(100) not null,
    primary key (CustomerID)
);

create table PromoCode (
	PromoCodeID int not null auto_increment,
    PercentOfDiscount decimal(5,2) null,
    FlatRateDiscount decimal(5,2) null,
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
    RoomTypeName varchar(20),
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
    `Name` varchar(60),
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
    AddOnItem varchar(60),
    Price decimal(5,2),
    primary key (AddOnID)
);

create table Reservation (
	ReservationID int not null auto_increment,
    CustomerID int not null,
    PromoCodeID int null,
    StartDate date,
    EndDate date,
    primary key (ReservationID),
    foreign key (CustomerID) references Customer(CustomerID),
    foreign key (PromoCodeID) references PromoCode(PromoCodeID)
);

create table Guest (
	GuestID int not null auto_increment,
	`Name` varchar(50) not null,
    Age int not null,
    ReservationID int not null,
    primary key (GuestID),
    foreign key (ReservationID) references Reservation(ReservationID)
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

create table Bill (
	BillID int not null auto_increment,
    ReservationID int not null,
    Tax decimal(5,2),
    Total decimal(6,2),
    primary key (BillID),
    foreign key (ReservationID) references Reservation(ReservationID)
);

create table BillDetail (
	BillDetailID int not null auto_increment,
    BillID int not null,
    `Name` varchar(50) not null,
    Price decimal(5,2) not null,
    primary key (BillDetailID),
    foreign key (BillID) references Bill(BillID)
);





