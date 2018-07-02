CREATE DEFINER=`root`@`localhost` PROCEDURE `AllDailyFromYesterdaySproc`(IN id int)
BEGIN
select 	sum(generated_electricity) as DailySum,
		min(generated_electricity) as Dailymax,
		max(generated_electricity) as Dailymin,
        avg(generated_electricity) as DailyAvg,
        reading_at
 from hourly_electricity
 where panel_id = id
 group by date(reading_at);

END
