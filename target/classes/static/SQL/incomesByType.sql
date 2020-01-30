CREATE PROCEDURE `incomesByType` (IN type VARCHAR(50))
BEGIN
	SELECT hou.number, SUM(incDet.price) as value
	FROM house AS hou
	JOIN income as inc ON inc.idhouse = hou.idhouse
	JOIN incomeDetail as incDet ON incDet.idincome = inc.idincome
	WHERE incDet.type = type
	GROUP BY hou.number;

END
