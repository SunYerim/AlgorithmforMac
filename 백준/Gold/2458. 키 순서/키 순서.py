import sys
n, m = map(int, input().split())

# 2차원 리스트 만들고 모든 값을 0으로 초기화
arr = [[0] * (n) for _ in range(n)]

for _ in range(m):
    v, e = map(int, input().split())
    # v가 e에게 가는 비용 1로 설정
    arr[v-1][e-1] = 1

# 플로이드-와샬 알고리즘 => 숫자 a, b에 대해 연결되어 있는지 검사
for k in range(n):
    for a in range(n):
        for b in range(n):
            if arr[a][k] == 1 and arr [k][b] == 1:
                arr[a][b] = 1

answer = 0
for i in range(n):
    count = 0
    for j in range(n):
        count += arr[i][j] + arr[j][i]
    # 노드의 개수보다 1만큼 작다는 것 (n-1) 이라는 것은 자기 자신 제외한 모든 노드와 비교 했다는 것임
    if count == n - 1:
        answer += 1
print(answer)