CREATE DEFINER=`root`@`localhost` PROCEDURE `incomesByType`(IN ID INT)
BEGIN
	SELECT hou.number, SUM(incDet.price) as value
	FROM house AS hou
	JOIN income as inc ON inc.idhouse = hou.idhouse
	JOIN incomeDetail as incDet ON incDet.idincome = inc.idincome
    JOIN condominium as cond ON hou.idcondom = cond.idcondom
	WHERE incDet.type = "Alicuota" AND inc.state = true 
	GROUP BY hou.number;
END