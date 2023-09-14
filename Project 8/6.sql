SELECT item_id AS id,item_name AS ItemName,COUNT(*) AS NumberofSales,
CONCAT('$',format(item_price,2)) AS price ,CONCAT('$', format(item_price*COUNT(*),2)) AS revenue
FROM menu_items NATURAL JOIN line_items
GROUP BY item_id
ORDER BY COUNT(*) desc ;
