Database initializer

CREATE TABLE user_details(
	user_id INT IDENTITY(1,1),
	name VARCHAR(60) NOT NULL,
	password VARCHAR(255) ,
	position VARCHAR(60) NOT NULL,
	is_active bit,
	CONSTRAINT user_pk PRIMARY KEY(user_id),
);

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
