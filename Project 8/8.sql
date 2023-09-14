
SELECT DISTINCT COUNT(DISTINCT item_price)AS AllDifferentPrices from
line_items NATURAL JOIN menu_items  ;