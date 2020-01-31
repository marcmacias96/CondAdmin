CREATE DEFINER=`root`@`localhost` PROCEDURE `repTypeOfExpencesByCondom`(IN Id INT)
BEGIN
SELECT SUM(expDet.price) As value, monthly.month, expDet.tipo
FROM expensedetail AS expDet 
JOIN expenses AS exp ON exp.idexpenses = expDet.idexpenses
JOIN monthlyaccounts AS monthly ON monthly.idmonthcounts = exp.idmonthcounts
JOIN condominium AS cond ON cond.idcondom = exp.idcondom
WHERE cond.idcondom = Id
GROUP BY expDet.tipo, monthly.month;
END