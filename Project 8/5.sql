SELECT item_id AS id,item_name AS ItemName,COUNT(*) AS NumberofSales FROM
menu_items NATURAL JOIN line_items
GROUP BY item_id
ORDER BY COUNT(*) desc ;
