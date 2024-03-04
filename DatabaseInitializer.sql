Database initializer

CREATE TABLE user_details(
	user_id INT IDENTITY(1,1),
	name VARCHAR(60) NOT NULL,
	password VARCHAR(255) ,
	position VARCHAR(60) NOT NULL,
	is_active bit,
	CONSTRAINT user_pk PRIMARY KEY(user_id),
);

CREATE TABLE room_details(
	room_id INT IDENTITY,
	room_name VARCHAR(30),
	capacity INT NOT NULL,
	video_conferencing BIT NOT NULL DEFAULT(0),
	white_board BIT NOT NULL DEFAULT(0),
	is_available BIT NOT NULL DEFAULT(1),
	CONSTRAINT room_pk PRIMARY KEY (room_id),
	CONSTRAINT capacity_positive CHECK(capacity > 0),
);

CREATE TABLE slot_master(
	slot_id INT IDENTITY,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	date DATE NOT NULL,
	CONSTRAINT slot_id_pk PRIMARY KEY(slot_id),
);


INSERT INTO room_details (room_name,capacity, video_conferencing, white_board,is_available)
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
