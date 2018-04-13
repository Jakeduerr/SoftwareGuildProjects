drop database if exists SuperHumanSightingsTest;

create database SuperHumanSightingsTest;

use SuperHumanSightingsTest;

create table Location (
	LocationID int not null auto_increment,
    `Name` varchar(75) not null,
    Description varchar(60) null,
    Address varchar(75) null,
    Latitude decimal(7,4) not null,
    Longitude decimal(7,4) not null,
    primary key (LocationID)
);

create table Sighting (
	SightingID int not null auto_increment,
    LocationID int not null,
    `Date` date not null,
    primary key (SightingID),
    foreign key (LocationID) references Location(LocationID)
);

create table SuperHuman (
	SuperHumanID int not null auto_increment,
    `Name` varchar(45) not null,
    Description varchar(150) not null,
    Powers varchar(200) not null,
    primary key (SuperHumanID)
);

create table SuperHumanSighting (
	SightingID int not null,
    SuperHumanID int not null,
    primary key (SightingID, SuperHumanID),
    foreign key (SightingID) references Sighting(SightingID),
    foreign key (SuperHumanID) references SuperHuman(SuperHumanID)
);

create table Organization (
	OrganizationID int not null auto_increment,
    `Name` varchar(45) not null,
    Description varchar(45) not null,
    Phone varchar(15),
    Address varchar(75),
    primary key (OrganizationID)
);

create table SuperHumanOrganization (
	SuperHumanID int not null,
    OrganizationID int not null,
    primary key (SuperHumanID, OrganizationID),
    foreign key (SuperHumanID) references SuperHuman(SuperHumanID),
    foreign key (OrganizationID) references Organization(OrganizationID)
);