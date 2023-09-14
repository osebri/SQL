SELECT shop_adress,sale_id,Date_and_time,item_name  FROM
sales_record LEFT JOIN tim_store
ON sales_record.Store=tim_store.Shop_ID
natural JOIN line_items
NATURAL JOIN menu_items
WHERE sale_type=2 and item_name='california turkey'
 ;