-- 코드를 입력하세요
SELECT
    uu.user_id as USER_ID,
    uu.nickname as NICKAME,
    t2.t TOTAL_SALES
FROM
    USED_GOODS_USER uu
JOIN (
    SELECT WRITER_ID, sum(price) as t
    FROM USED_GOODS_BOARD
    WHERE status = 'DONE'
    group by writer_id
    having t >= 700000
) t2
ON
    uu.USER_ID = t2.WRITER_ID
order by
    TOTAL_SALES asc;