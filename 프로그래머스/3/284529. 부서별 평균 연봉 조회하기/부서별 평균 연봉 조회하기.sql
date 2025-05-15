-- 코드를 작성해주세요
SELECT
    hd.DEPT_ID as DEPT_ID,
    hd.DEPT_NAME_EN as DEPT_NAME_EN,
    he.avg_sal as AVG_SAL
FROM
    HR_DEPARTMENT hd
JOIN(
    SELECT 
        DEPT_ID as di, 
        ROUND(avg(sal), 0) as avg_sal
    FROM HR_EMPLOYEES
    GROUP BY DEPT_ID
) he
ON hd.dept_id = he.di
ORDER BY
    AVG_SAL desc
