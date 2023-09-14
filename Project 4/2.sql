INSERT into sales_type VALUES(1,'In store');
INSERT into sales_type VALUES(2,'Drive thru');
INSERT into sales_type VALUES(3,'Online-Pick up');
INSERT into sales_type VALUES(4,'Online-Delivery');
INSERT into sales_type VALUES(5,'Phone In-Delivery');

INSERT INTO menu_categories VALUES(11,'Hot Beverage');
INSERT INTO menu_categories VALUES(12,'Cold Beverage');
INSERT INTO menu_categories VALUES(13,'Handcrafted Sandwich');
INSERT INTO menu_categories VALUES(14,'Bakery Fresh');

INSERT INTO tim_store VALUES(101, '612 King St');
INSERT INTO tim_store VALUES(102, '534 Union St'); 
INSERT INTO tim_store VALUES(103, '290 Main St'); 
INSERT INTO tim_store VALUES(104, '375 Smythe St'); 

INSERT INTO menu_items VALUES(1, 'Coffee (Original Blend, Dark Roast or Decaf)	Small', 1.59, 11);
INSERT INTO menu_items VALUES(2, 'Coffee (Original Blend, Dark Roast or Decaf)	Medium', 1.79, 11);
INSERT INTO menu_items VALUES(3, 'Coffee (Original Blend, Dark Roast or Decaf)	Large', 1.99, 11);
INSERT INTO menu_items VALUES(4, 'Iced Coffee (Original or Dark Roast)	Small', 1.99, 12);
INSERT INTO menu_items VALUES(5, 'Iced Coffee (Original or Dark Roast)	Medium', 2.29, 12);
INSERT INTO menu_items VALUES(6, 'Iced Coffee (Original or Dark Roast)	Large', 2.79, 12);
INSERT INTO menu_items VALUES(7, 'California Turkey', 6.49, 13);
INSERT INTO menu_items VALUES(8, 'Turkey Bacon Club', 6.49, 13);
INSERT INTO menu_items VALUES(9, 'Avocado BLT', 5.49, 13); 

INSERT INTO employees VALUES(1000, 'Caleb', 'Charles');
INSERT INTO employees VALUES(1001, 'Walter', 'White');
INSERT INTO employees VALUES(1002, 'Linda', 'Steve');  
INSERT INTO employees VALUES(1003, 'Leonardo', 'Gates');

INSERT INTO sales_record VALUES(10001, '2022-03-08 15:00',1,101,1002);
INSERT INTO sales_record VALUES(10002, '2022-03-08 10:00',3,103,1001);

INSERT INTO line_items VALUES(1,10001,4);
INSERT INTO line_items VALUES(1,10002,7); 
INSERT INTO line_items VALUES(2,10002,5);
INSERT INTO line_items VALUES(3,10002,6);
    