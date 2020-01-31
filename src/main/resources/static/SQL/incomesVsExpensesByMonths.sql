CREATE DEFINER=`root`@`localhost` PROCEDURE `incomesVsExpensesByMonths`(IN Year INT)
BEGIN
	SELECT month.month, SUM(month.income) AS incomes, SUM(month.expenses) AS expences
	FROM monthlyaccounts AS month
	JOIN annualcounts AS year ON month.idannualcounts = year.idannualcounts
	WHERE year.year = year
	GROUP BY month.month;

END