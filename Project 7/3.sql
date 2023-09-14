SELECT sale_id,line_item_id AS Line, item_name FROM line_items 
NATURAL JOIN menu_items
NATURAL JOIN sales_record
WHERE Employee=1002 AND item_category=13 ;