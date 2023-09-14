SELECT item_category AS categoryID ,menu_types AS categoryName,COUNT(*) AS MenuItemCount
FROM menu_items LEFT OUTER JOIN menu_categories
ON menu_items.Item_category=menu_categories.Menu_ID
WHERE menu_types='Hot Beverage' ;
