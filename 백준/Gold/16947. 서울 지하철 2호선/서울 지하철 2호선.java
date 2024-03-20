import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BFS -> 순환선인지 아닌지 cycle 판별 메서드
// 2차원 arraylist -> 연결관계를 표시
// 순환선에 속해있는지 안있는지 boolean 배열 하나
public class Main {
    static int n;
    static int[] distance; // 출발지점부터 거리 계산
    static boolean[] isCycle;
    static boolean[] visited;
    static int[] ansArr;
    static Stack<Integer> stack = new Stack<>();;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        list = new List[n+1];

        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

//        isCycle = new boolean[n+1]; // 1번부터 시작
//        visited = new boolean[n+1];
//        ansArr = new int[n+1];
//        distance = new int[n+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        // 사이클 판별은 dfs
        // 순환선과 지선간의 최단 거리 계산은 bfs

        checkCycle(); // 사이클 확인
        getDistance(); // 거리 구하기

    }

    // DFS
    private static boolean dfs(int start, int i, int cnt) {
        // 이미 방문되었고 시작점이랑 똑같다면 cycle이 형성됨.
        for (int next: list[i]) {
            if (next == start) {
                if (cnt > 1) // 바로 갔다가 돌아오는 경우 배제
                    return isCycle[i] = true;
                else
                    continue;
            }
            // 다음이 사이클이거나, 현재가 사이클이거나, 다음을 이미 방문했을때는
            if (isCycle[next] || isCycle[i] || visited[next]) {
                continue;
            }
            visited[next] = true;
            isCycle[i] = dfs(start, next, cnt+1);
        }
        return isCycle[i];

    }

    private static void checkCycle() {
        isCycle = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            if (!isCycle[i]) {
                visited=new boolean[n+1];
                dfs(i, i, 0);
            }
        }
    }

    // 순환선 안 속해있는 역들 길 구하기
    private static void getDistance() {
        distance = new int[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; ++i) {
            if (isCycle[i] && list[i].size() > 2) {
                searchRoute(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(distance[i] + " ");
        }
        System.out.println(sb.toString());
    }

    private static void searchRoute(int i) {
        for (int next: list[i]) {
            if (isCycle[next] || distance[next] > 0) continue;
            distance[next] = distance[i] + 1;
            searchRoute(next);
        }
    }
}