SELECT DISTINCT menu_types AS CATEGORY FROM
line_items NATURAL JOIN menu_items
NATURAL JOIN menu_categories ;