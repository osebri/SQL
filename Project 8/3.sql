SELECT DISTINCT item_category,Menu_types,COUNT(*)
FROM menu_items LEFT OUTER JOIN menu_categories
ON menu_items.Item_category=menu_categories.Menu_ID
GROUP BY item_category;
