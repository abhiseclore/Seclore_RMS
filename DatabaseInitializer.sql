Database initializer



CREATE TABLE user_details(
	user_id INT IDENTITY,
	name VARCHAR(60) NOT NULL,
	position VARCHAR(60) NOT NULL,
	password VARCHAR(20) DEFAULT 'Seclore@123',
	is_actice bit DEFAULT 1,
	CONSTRAINT user_pk PRIMARY KEY(user_id),
);


CREATE TABLE room_details(
	room_id INT IDENTITY,
	room_name VARCHAR(60),
	seating_capacity INT NOT NULL,
	audio_video BIT NOT NULL DEFAULT 0,
	white_board BIT NOT NULL DEFAULT 0,
	is_available BIT NOT NULL DEFAULT 1, 
	CONSTRAINT room_pk PRIMARY KEY (room_id),
	CONSTRAINT seating_capacity_positive CHECK(seating_capacity > 0),
);


 
CREATE TABLE slot_master(
	slot_id INT IDENTITY,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	date DATE,
	CONSTRAINT slots_pk PRIMARY KEY(slot_id),
);


CREATE TABLE booking_details(
	booking_id INT IDENTITY,
	room_id INT NOT NULL,
	user_id INT NOT NULL,
	description VARCHAR(500),
	status VARCHAR(60),
	CONSTRAINT bookings_pk PRIMARY KEY(booking_id,room_id,user_id),
	CONSTRAINT bookings_room_fk FOREIGN KEY(room_id) REFERENCES room_details(room_id),
	CONSTRAINT bookings_user_fk FOREIGN KEY(user_id) REFERENCES user_details(user_id),
	CONSTRAINT check_status CHECK(status in ('BOOKED', 'CANCELLED', 'REQUESTED'))
);

create table booking_slots(
booking_id int,
slot_id int,
is_slot_active bit not null default 0,
CONSTRAINT booking_slots_pk PRIMARY KEY(booking_id,slot_id),
CONSTRAINT booking_slots_fk FOREIGN KEY(slot_id) REFERENCES slot_master(slot_id),
);


INSERT INTO room_details (room_name,seating_capacity, audio_video, white_board,is_available)
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



INSERT INTO user_details (name, position)
VALUES ('Manohar Chaturvedi', 'Admin'),('John Doe', 'Manager'), ('Jane Doe', 'Assistant Manager'), ('Alice Smith', 'Sales Representative'), ('Bob Johnson', 'Sales Representative'), ('Charlie Brown', 'Sales Representative'), ('David Lee', 'Marketing Manager'), ('Emily Davis', 'Marketing Coordinator'), ('Frank Wilson', 'IT Manager'), ('Grace Kim', 'IT Support Specialist'), ('Henry Lee', 'IT Support Specialist');


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
