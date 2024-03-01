'''logic
    1. dfs를 이용한 최대최소를 구하는 방법'''
import sys

input = sys.stdin.readline
num = int(input())

num_list = list(map(int, input().split()))

# 연산자
op = list(map(int, input().split()))

# 최솟값 최댓값 초기화 !
max_num = -1e9
min_num = 1e9

# dfs 메소드 구현
def dfs(depth, total, add, sub, mul, div):
    global max_num, min_num
    if depth == num:
        max_num = max(total, max_num)
        min_num = min(total, min_num)
        return
    
    if add:
        dfs(depth +1, total + num_list[depth], add-1, sub, mul, div)
    if sub:
        dfs(depth+1, total - num_list[depth], add, sub-1, mul, div)
    if mul:
        dfs(depth+1, total * num_list[depth], add, sub, mul-1, div)
    if div:
        dfs(depth+1, int(total/num_list[depth]), add, sub, mul, div-1)
    
dfs(1, num_list[0], op[0], op[1], op[2], op[3])
print(max_num)
print(min_num)