CREATE PROCEDURE `incomesVsExpensesByMonths` (IN Year INT)
BEGIN
	SELECT SUM(month.income) AS incomes, SUM(month.expenses) AS expences, month.month
	FROM monthlyaccounts AS month
	JOIN annualcounts AS year ON month.idannualcounts = year.idannualcounts
	WHERE year.year = year
	GROUP BY month.month;

END
