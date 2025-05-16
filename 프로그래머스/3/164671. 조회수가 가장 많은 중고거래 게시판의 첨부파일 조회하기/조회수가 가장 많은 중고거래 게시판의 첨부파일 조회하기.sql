-- 코드를 입력하세요
SELECT
    concat('/home/grep/src/', ub.board_id, '/', uf.file_id, uf.file_name, uf.file_ext) as file
FROM
    USED_GOODS_FILE uf
JOIN (
    SELECT
        board_id, views
    FROM
        USED_GOODS_BOARD
    ORDER BY
        views desc
    limit 1
) ub
ON uf.BOARD_ID = ub.BOARD_ID
ORDER BY uf.file_id desc;
