-- 코드를 입력하세요
SELECT ai.animal_id as ANIMA_ID, ai.name as NAME from ANIMAL_INS ai join ANIMAL_OUTS ao on ai.animal_id = ao.animal_id where ai.DATETIME > ao.DATETIME order by ai.datetime;