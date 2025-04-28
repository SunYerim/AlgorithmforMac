-- 코드를 입력하세요
SELECT HOUR(DATETIME) as HOUR, count(*) as COUNT from animal_outs where HOUR(DATETIME) >= 9 AND HOUR(DATETIME) < 20 group by HOUR order by HOUR(DATETIME);