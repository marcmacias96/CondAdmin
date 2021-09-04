CREATE DEFINER=`root`@`localhost` PROCEDURE `repTypeOfExpensesOnMonthByCondom`(IN Id INT,IN month INT)
BEGIN
SELECT expDet.tipo,SUM(expDet.price) As value
FROM expensedetail AS expDet 
JOIN expenses AS exp ON exp.idexpenses = expDet.idexpenses
JOIN monthlyaccounts AS monthly ON monthly.idmonthcounts = exp.idmonthcounts
JOIN condominium AS cond ON cond.idcondom = exp.idcondom
WHERE cond.idcondom = Id AND monthly.month = month
GROUP BY expDet.tipo;
END