SELECT * FROM sales_record
WHERE sale_id = (SELECT MAX(sale_id) FROM sales_record);
SELECT sale_id,item_id,item_name FROM
line_items NATURAL JOIN menu_items
WHERE sale_id = (SELECT MAX(sale_id) FROM sales_record);