use SecloreRMSdb;

CREATE TABLE slot_master(
	slot_id INT IDENTITY,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	date DATE NOT NULL,
	CONSTRAINT slot_id_pk PRIMARY KEY(slot_id),
);

go
DECLARE @startDate DATE = '2024-03-01';
 
WHILE @startDate <= '2024-03-10'
BEGIN
	DECLARE @startTime Time = '00:30:00';
	INSERT INTO slot_master(date,start_Time,end_Time) VALUES ( @startDate,'00:00:00','00:30:00');
	
	WHILE @startTime != '00:00:00'
	BEGIN
		DECLARE @endTime Time =@startTime;
 
		 SET @endTime =  DATEADD(MINUTE, 30, @endTime)
 
		 INSERT INTO slot_master(date,start_Time,end_Time)
		 VALUES ( @startDate,@startTime,@endTime);
 
		 SET @startTime = @endTime;
	END
	SET @startDate =  DATEADD(DAY, 1, @startDate);
END
go

select * from slot_master

----------------------------------------------------------------------------------------------------------------


CREATE TABLE room_details(
	room_id INT IDENTITY,
	room_name VARCHAR(30),
	capacity INT NOT NULL,
	audio_video BIT NOT NULL DEFAULT(0),
	white_board BIT NOT NULL DEFAULT(0),
	is_available BIT NOT NULL DEFAULT(1),
	CONSTRAINT room_pk PRIMARY KEY (room_id),
	CONSTRAINT capacity_positive CHECK(capacity > 0),
);

INSERT INTO room_details (room_name,capacity, audio_video, white_board,is_available)
VALUES 
('Blue room',5, 1, 0,1), 
('Red room',10, 0, 1,1), 
('Green room',15, 1, 1,1), 
('Purple room',20, 0, 0,0), 
('Black room',25, 1, 0,1), 
('Yello room',30, 0, 1,0), 
('White room',35, 1, 1,1), 
('Pink room',40, 0, 0,0), 
('Neon room',45, 1, 0,1), 
('Dark room',50, 0, 1,1);

select * from room_details
----------------------------------------------------------------------------------------------------------


CREATE TABLE user_details(
	user_id INT IDENTITY,
	name VARCHAR(30) NOT NULL,
	position VARCHAR(30) NOT NULL,
	password VARCHAR(20) DEFAULT 'Seclore@123' not null,
	is_active bit DEFAULT 1 not null,
	CONSTRAINT user_pk PRIMARY KEY(user_id),
);


INSERT INTO user_details (name, position)
VALUES ('Manohar Chaturvedi', 'Admin'),
('John Doe', 'Manager'), ('Jane Doe', 'Assistant Manager'), 
('Alice Smith', 'Sales Representative'), ('Bob Johnson', 'Sales Representative'), 
('Charlie Brown', 'Sales Representative'), ('David Lee', 'Marketing Manager'), 
('Emily Davis', 'Marketing Coordinator'), ('Frank Wilson', 'IT Manager'), 
('Grace Kim', 'IT Support Specialist'), ('Henry Lee', 'IT Support Specialist');

select * from user_details
--------------------------------------------------------------------------------------------------------

CREATE TABLE booking_details(
	booking_id INT IDENTITY,
	room_id INT NOT NULL,
	user_id INT NOT NULL,
	description VARCHAR(100),
	status VARCHAR(20),
	CONSTRAINT bookings_pk PRIMARY KEY(booking_id),
	CONSTRAINT bookings_room_fk FOREIGN KEY(room_id) REFERENCES room_details(room_id),
	CONSTRAINT bookings_user_fk FOREIGN KEY(user_id) REFERENCES user_details(user_id),
	CONSTRAINT check_status CHECK(status in ('BOOKED', 'CANCELLED', 'REQUESTED'))
);

-------------------------------------------------------------------------------------------------------------------------------

create table booking_slots(
	booking_id int ,
	slot_id int,
	is_slot_active bit not null default 0,
	room_id INT NOT NULL,
	CONSTRAINT booking_slots_pk PRIMARY KEY(booking_id,slot_id),
	CONSTRAINT booking_slots_fk FOREIGN KEY(slot_id) REFERENCES slot_master(slot_id),
	CONSTRAINT booking_id_fk FOREIGN KEY(booking_id) REFERENCES booking_details(booking_id),
	CONSTRAINT booking_room_id_fk FOREIGN KEY(room_id) REFERENCES room_details(room_id),
);

-----------------------------------------------------------------------------------------------------------------

INSERT INTO booking_details (room_id, user_id, description, status)
VALUES
    (1, 1, 'Team Meeting', 'BOOKED'),
    (3, 2, 'Client Presentation', 'CANCELLED'),
    (1, 3, 'Training Session', 'REQUESTED');

truncate table booking_slots
INSERT INTO booking_slots (booking_id, slot_id, is_slot_active, room_id)
VALUES
    (1, 1, 1, 1),
	(1,2,1,1),
	(1,3,1,1),
    (2, 2, 0, 3),
    (3, 1, 0, 1);

select * from booking_details
select * from booking_slots

---------------------------------------------------------------------------------------------------------------------------
go
CREATE VIEW all_bookings as 
SELECT X.room_id,X.slot_id,b.booking_id,X.start_time,X.end_time, X.date, CASE
            WHEN b.is_slot_active is null 
               THEN 0
	       Else b.is_slot_active
            END as is_booked
	   FROM (SELECT * FROM slot_master s
CROSS JOIN
(select DISTINCT(room_id) from booking_details) r) X 
LEFT JOIN
booking_slots b
ON 
b.room_id= X.room_id and b.slot_id = X.slot_id 
go
select * from all_bookings
go
--------------------------------------------------------------------------------------------------------------------------------
go
create procedure getStartEndTime @booking_id int
as
select booking_id,room_id,start_time,end_time,date from
((SELECT TOP 1 * FROM all_bookings where booking_id = @booking_id)
UNION 
(SELECT TOP 1 * FROM all_bookings where booking_id = @booking_id ORDER BY date,end_time desc)) t;
go

exec getStartEndTime 1
-----------------------------------------------------------------------------------------------------------------------------------

go
create procedure getBookedRooms @start_time time,@end_time time,@start_date date,@end_date date
as
select X.room_id,count(X.Room_Status) as status from (
select room_id,case
	when is_booked = 1 and (start_time between @start_time and @end_time) and (date between @start_date and @end_date) and (end_time between @start_time and @end_time)
		then 0
		else 1
end as Room_Status
from all_bookings) X where X.Room_Status = 0 group by X.room_id 
go
exec getBookedRooms '00:00:00.0000000','06:00:00.0000000','2024-03-01','2024-03-07'
----------------------------------------------------------------------------------------------------------------------------

