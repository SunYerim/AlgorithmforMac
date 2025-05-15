-- 코드를 입력하세요
SELECT
    u.USER_ID,
    u.NICKNAME,
    CONCAT(u.city, ' ' , u.street_address1, ' ', IFNULL(u.street_address2, '')) as 전체주소,
    CONCAT(
        SUBSTR(u.TLNO, 1, 3), '-',
        SUBSTR(u.TLNO, 4, 4), '-',
        SUBSTR(u.TLNO, 8)
    ) as 전화번호
FROM USED_GOODS_USER u
JOIN (
    SELECT WRITER_ID
    FROM USED_GOODS_BOARD
    GROUP BY WRITER_ID
    HAVING count(*) >= 3
) b on u.user_id = b.writer_id
order by u.user_id desc;