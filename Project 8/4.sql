SELECT distinct item_name, item_id FROM
line_items NATURAL JOIN menu_items
GROUP BY item_name ;