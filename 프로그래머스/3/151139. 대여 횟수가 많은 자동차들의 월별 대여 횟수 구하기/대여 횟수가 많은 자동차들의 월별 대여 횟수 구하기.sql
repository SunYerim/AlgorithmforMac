-- 코드를 입력하세요
SELECT month(start_date) as MONTH, CAR_ID, COUNT(*) as RECORDS 
from car_Rental_company_rental_history 
where 
    start_date between '2022-08-01' AND '2022-10-31'
    and car_id in (
        select car_id
        from car_rental_company_rental_history
        where start_Date between '2022-08-01' and '2022-10-31'
        group by car_id
        having count(*) >= 5
    )
group by 
    month(start_Date), car_id
order by 
    MONTH asc, CAR_ID desc;