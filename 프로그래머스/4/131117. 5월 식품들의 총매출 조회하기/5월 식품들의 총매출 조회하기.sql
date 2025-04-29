-- 코드를 입력하세요
SELECT fp.product_id as PRODUCT_ID, fp.product_name as PRODUCT_NAME, sum(fo.amount * fp.price) as TOTAL_SALES 
from food_order fo left join food_product fp on fo.product_id = fp.product_id
where date_format(fo.produce_date, '%Y-%m') = '2022-05'
group by product_id 
order by total_sales desc, fp.product_id;