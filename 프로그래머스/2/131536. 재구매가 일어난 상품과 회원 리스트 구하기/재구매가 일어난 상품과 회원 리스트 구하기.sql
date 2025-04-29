-- 코드를 입력하세요
SELECT user_id as USER_ID, product_id as PRODUCT_ID
from online_sale
group by user_id, product_id
having count(*) >= 2
order by user_id, product_id desc