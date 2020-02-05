CREATE DEFINER=`root`@`localhost` PROCEDURE `incomesVsExpensesByMonths`(IN Id INT)
BEGIN
SELECT month.month, SUM(month.income) AS incomes, SUM(month.expenses) AS expences
	FROM monthlyaccounts AS month
	JOIN annualcounts AS year ON month.idannualcounts = year.idannualcounts
    JOIN condominium AS cond ON year.idcondom = cond.idcondom
	WHERE year.year = year AND cond.idcondom = Id
	GROUP BY month.month;
END