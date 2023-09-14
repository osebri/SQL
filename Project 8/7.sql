SELECT DISTINCT CONCAT('$',FORMAT(Item_price,2)) AS AllDifferentPrices
FROM line_items
NATURAL JOIN menu_items
GROUP BY item_price ;