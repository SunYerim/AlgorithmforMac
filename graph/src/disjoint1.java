import java.util.*;
public class disjoint1 {
    public static int e, v; // 노드 개수와 간선 개수
    public static int[] parent = new int[10001]; // 부모 테이블 초기화

    // 특정 원소가 속한 집합을 찾기
    public static int findParent(int x){
        if (parent[x] == x)
            return x;
        return findParent(parent[x]);
    }

    // 두 원소가 속한 집합을 합치기
    public static void unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();

        // 부모 테이블에서 부모를 자기 자신으로 초기화
        for (int i = 1; i <= v; i++){
            parent[i] = i;
        }

        // Union 연산을 각각 수행
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            unionParent(a, b);
        }

        // 각 원소가 속한 집합 출력하기
        System.out.print("각 원소가 속한 집합: ");
        for (int i = 1; i <= v; i++) {
            System.out.print(findParent(i) + " ");
        }
        System.out.println();

        // 부모 테이블 내용 출력하기
        System.out.print("부모 테이블: ");
        for (int i = 1; i <= v; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
    }

}
