-- 코드를 작성해주세요
SELECT
    year(e1.DIFFERENTIATION_DATE) as YEAR,
    e2.max_size - e1.size_of_colony as YEAR_DEV,
    e1.id as ID
FROM ecoli_data e1
join (
    SELECT
        YEAR(DIFFERENTIATION_DATE) AS YEAR,
        MAX(size_of_colony) as max_size
    FROM ECOLI_DATA
    GROUP BY year(DIFFERENTIATION_DATE)
) e2
ON YEAR(e1.DIFFERENTIATION_DATE) = e2.year
order by
    year asc,
    year_dev asc;