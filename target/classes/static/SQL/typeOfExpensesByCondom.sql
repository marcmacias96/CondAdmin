CREATE PROCEDURE `typeOfExpensesByCondom` (IN ID INT)
BEGIN
	SELECT expDet.tipo, SUM(expDet.price) AS value
	FROM condominium AS cond
	JOIN expenses as exp ON  exp.idcondom = cond.idcondom
	JOIN expensedetail as expDet ON expDet.idexpenses = exp.idexpenses
	WHERE cond.idcondom = ID
	group by expDet.tipo;
END