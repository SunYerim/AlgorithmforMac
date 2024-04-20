import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] income, times, dp;
    static int T, n, k, w;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            income = new int[n+1];
            times = new int[n+1];
            dp = new int[n+1];

            // 리스트 초기화
            graph.clear();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine());
            // 건설시간 입력받기
            for (int i = 1; i <= n; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }
            // k개만큼 입력받기
            for (int i = 1; i <= k; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graph.get(start).add(end);
                income[end]+=1; // 진입차수 증가
            }
            w = Integer.parseInt(br.readLine());

            topologySort();

            sb.append(dp[w]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void topologySort() {
        Queue<Integer> queue = new LinkedList<>();

        // 진입차수 0인 노드부터
        for (int i = 1; i <= n; i++) {
            if (income[i] == 0) {
                queue.offer(i);
                dp[i] = times[i];
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 연결된 노드들 진입차수에서 제거
            for (int next : graph.get(curr)) {
                income[next] -= 1;
                dp[next] = Math.max(dp[next], dp[curr] + times[next]);
                // 여기서 새롭게 진입차수 0이 되는 노드 있으면 큐에 넣는다.
                if (income[next] == 0) {
                    queue.offer(next);
                }
            }

        }
    }

    static class Node {
        private int start, end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}