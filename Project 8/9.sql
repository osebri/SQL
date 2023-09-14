
SELECT * FROM menu_items
WHERE item_price = (SELECT Min(item_price) FROM menu_items) ;