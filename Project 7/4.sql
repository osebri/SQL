SELECT sale_id,line_item_id AS Line, item_name FROM line_items 
NATURAL JOIN menu_items
NATURAL JOIN menu_categories
NATURAL JOIN sales_record
left JOIN  employees
ON sales_record.Employee = employees.Employee_ID 
WHERE First_name='linda' AND last_name='steve' AND menu_types='Handcrafted sandwich' ; 