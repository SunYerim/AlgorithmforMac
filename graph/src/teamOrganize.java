/* logic
    1. 서로소 집합 알고리즘으로 접근
    2. union-find 연산*/

import java.util.*;
public class teamOrganize {
    public static int e, v; // 노드 개수와 간선 개수
    public static int[] parent = new int[10001]; // 부모 테이블 초기화

    // find연산
    public static int findParent(int x) {
        if (parent[x] == x)
            return x;
        return findParent(parent[x]);
    }

    // union 연산
    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        e = sc.nextInt();
        v = sc.nextInt();

        // 부모 테이블에서 부모를 자기 자신으로 초기화
        for (int i = 1; i <=  v; i++) {
            parent[i] = i;
        }

        // 각 연산 하나씩 확인
        for (int i = 0; i < v; i++) {
            int num = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (num == 0) {
                unionParent(a, b);
            }
            if (num == 1) {
                if (findParent(a) == findParent(b)) {
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
            }
        }
    }
}
