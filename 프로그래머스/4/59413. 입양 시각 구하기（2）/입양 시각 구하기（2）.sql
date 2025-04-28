-- 코드를 입력하세요
WITH RECURSIVE hours AS (
    SELECT 0 AS hour
    UNION ALL
    SELECT hour + 1
    FROM hours
    WHERE hour < 23
)

SELECT h.hour as HOUR, count(a.datetime) as COUNT from hours h left join animal_outs a ON HOUR(a.DATETIME) = h.hour group by h.hour order by h.hour;