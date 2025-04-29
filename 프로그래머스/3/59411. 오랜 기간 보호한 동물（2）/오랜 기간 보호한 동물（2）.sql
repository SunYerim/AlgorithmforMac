-- 코드를 입력하세요
SELECT ai.animal_id as ANIMAL_ID, ai.name as NAME from animal_ins ai left join animal_outs ao on ai.animal_id = ao.animal_id order by datediff(ao.datetime, ai.datetime) desc limit 2;