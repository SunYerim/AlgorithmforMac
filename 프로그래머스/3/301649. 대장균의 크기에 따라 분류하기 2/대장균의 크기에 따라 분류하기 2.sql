-- 코드를 작성해주세요
SELECT ID,
        CASE
            WHEN (RN / TOTAL_CNT) <= 0.25 THEN 'CRITICAL'
            WHEN (RN / TOTAL_CNT) <= 0.50 THEN 'HIGH'
            WHEN (RN / TOTAL_CNT) <= 0.75 THEN 'MEDIUM'
            ELSE 'LOW'
        END AS COLONY_NAME
FROM (
    SELECT ID,
            ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) AS RN,
            COUNT(*) OVER () AS TOTAL_CNT
    FROM ECOLI_DATA
) A
ORDER BY ID ASC;