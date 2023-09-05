/* logic
    1. 최소신장트리 문제인듯?
    2. 근데 분리된 두 마을이라고 했으므로 -> 2개의 최소 신장트리 만들어야 되는 거 같음.
    3. 그러면 크루스칼 알고리즘 써서 문제 해결한 뒤에 그 중에 유지비 제일 많이드는 길 없애면 될 듯
    */
import java.util.*;

public class civilDivide {

    public static int n, m;
    public static int[] parent = new int[10001];
    public static ArrayList<Edge> edges = new ArrayList<>();
    public static int result = 0;
    public static int last = 0;
    // find
    public static int findParent(int x) {
        if (x == parent[x])
            return x;
        else
            return parent[x] = findParent(parent[x]);
    }

    // union
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

        n = sc.nextInt(); // 집
        m = sc.nextInt(); // 길

        // 부모 테이블 상에서 부모를 자신으로 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 정보 입력 받기 m줄에 걸쳐서
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(a, b, cost));
        }

        // cost순으로 간선을 정렬
        Collections.sort(edges);

        // 간선 하나씩 확인하면서 일단 사이클 생성 안 되는 경우에만 집합에 포함을 시킨다.
        for (int i = 0; i < edges.size(); i++){
            int cost = edges.get(i).getDistance();
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();

            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                last = cost;
                result += cost;

            }
        }

        System.out.println(result - last);

    }
}
