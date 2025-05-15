-- 코드를 작성해주세요
SELECT 
    fi.id as ID,
    fni.fish_name as FISH_NAME,
    fi.length as LENGTH
FROM FISH_INFO fi
JOIN (
    SELECT fish_type, MAX(length) AS max_length
    FROM FISH_INFO
    WHERE length is not null
    group by fish_type
) max_fish
ON fi.fish_type = max_fish.fish_type AND fi.length = max_fish.max_length
JOIN fish_name_info fni on fi.fish_type = fni.fish_type
order by fi.id asc;