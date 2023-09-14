SELECT * FROM base ;
SELECT * FROM truck ;
SELECT * FROM types ;
SELECT  * FROM 
truck NATURAL JOIN base ;
SELECT  SERIAL_number FROM 
base NATURAL JOIN truck 
WHERE base_state='tn' ;

SELECT distinct base_state, type_code FROM
truck NATURAL JOIN base
WHERE TYPE_code =2 ;

SELECT DISTINCT base_city FROM
truck NATURAL JOIN base
NATURAL JOIN types
WHERE description like 'single box';
 