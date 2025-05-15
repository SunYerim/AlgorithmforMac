-- 코드를 입력하세요
SELECT
    car_type as CAR_TYPE,
    count(*) as CARS
FROM
    CAR_RENTAL_COMPANY_CAR
WHERE
    options like '%통풍시트%'
    or options like '%열선시트%'
    or options like '%가죽시트%'
group by
    car_type
order by
    car_type asc;