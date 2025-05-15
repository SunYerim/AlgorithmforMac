-- 코드를 입력하세요
SELECT 
    r.FOOD_TYPE,
    r.REST_ID,
    r.REST_NAME,
    r.FAVORITES
FROM REST_INFO r
JOIN (
    SELECT food_type, MAX(favorites) as max_fav
    FROM rest_info
    GROUP BY food_type
) mf
ON r.food_type = mf.food_type AND r.favorites = mf.max_fav
ORDER BY
    r.FOOD_TYPE desc;