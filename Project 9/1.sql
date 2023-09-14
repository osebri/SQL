DELIMITER $$
CREATE procedure adjustPrice5(itemID INT)
BEGIN
if ( EXISTS(SELECT item_id FROM menu_items WHERE item_id=itemID)) then
	if((SELECT item_price FROM menu_items WHERE item_id=itemID)<2) then
		UPDATE menu_items
			SET item_price = (item_price*102)/100
			WHERE item_id=itemID;
	ELSEIF ((SELECT item_price FROM menu_items WHERE item_id=itemID)<4) then
		UPDATE menu_items
			SET item_price = (item_price*104)/100
			WHERE item_id=itemID;
	ELSE 
		UPDATE menu_items
			SET item_price = (item_price*105)/100
			WHERE item_id=itemID;
	END if ;
ELSE SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT ='item not found' ;
END if;
END$$
