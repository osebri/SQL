DELIMITER $$
CREATE PROCEDURE multisale13(itemID INT,quantity INT,saletype INT,storeID INT,employeeID INT)
BEGIN
declare START INT DEFAULT 0;
if(EXISTS(SELECT item_id FROM menu_items WHERE item_id=itemID)
AND EXISTS(SELECT sale_type_id FROM sales_type WHERE sale_type_id=saletype) 
AND EXISTS(SELECT employee_id FROM employees WHERE employee_id=employeeid)
AND EXISTS(SELECT shop_id FROM tim_store WHERE shop_id=storeID)) then
Select
@lastint:=MAX(sale_id)
FROM sales_record;
INSERT INTO sales_record VALUES(@lastint+1,CURDATE(),saletype,storeid,employeeid);
while(START < quantity) DO
	INSERT INTO line_items VALUES(START+1,(SELECT MAX(sale_id) FROM sales_record),itemID);
	set START = START + 1;
END while ;
SELECT item_price*quantity AS TotalSalePrice FROM menu_items 
	WHERE item_id=itemID;
ELSE SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT ='invalid input' ;
END if;
END$$

