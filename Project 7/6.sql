SELECT distinct First_name, last_name,shop_adress FrOM
sales_record NATURAL JOIN line_items
NATURAL JOIN menu_items
LEFT JOIN tim_store 
ON sales_record.Store=tim_store.Shop_ID
LEFT JOIN employees
ON sales_record.Employee=employees.Employee_ID 
WHERE item_id=7
;