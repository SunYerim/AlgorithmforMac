-- 코드를 입력하세요
SELECT substring(product_code from 1 for 2) as CATEGORY, count(product_code) as PRODUCTS from product group by CATEGORY order by product_code;