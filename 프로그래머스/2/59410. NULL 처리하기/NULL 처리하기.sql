-- 코드를 입력하세요
SELECT 
    animal_type as ANIMAL_TYPE,
    case
        when name is null 
        then 'No name'
        when name is not null
        then name
    end
    as NAME, 
    sex_upon_intake as SEX_UPON_INTAKE from animal_ins order by animal_id;