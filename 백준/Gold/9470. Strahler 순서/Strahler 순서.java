import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> list;
    static int[] indegree;
    static int[] maxOrder;
    static int[] count;
    static int[] order;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i = 0; i <= M; i++) {
                list.add(new ArrayList<>());
            }

            indegree = new int[M + 1];
            maxOrder = new int[M + 1]; // 들어오는 순서 중 최댓값
            count = new int[M + 1]; // 최댓값이 몇 번 들어왔는지
            order = new int[M + 1]; // 각 노드의 최종 strahler 순서

            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.get(a).add(b);
                indegree[b]++;
            }

            topologySort(M);

            sb.append(K).append(" ").append(order[M]).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void topologySort(int m) {
        Queue<Integer> q = new LinkedList<>();

        // 1. 진입차수가 0인 노드 찾기
        for (int i = 1; i <= m; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                order[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : list.get(curr)) {
                // 2. curr이 다음 노드의 최댓값보다 큰지 확인
                if (order[curr] > maxOrder[next]) {
                    maxOrder[next] = order[curr];
                    count[next] = 1;
                } else if (order[curr] == maxOrder[next]) {
                    count[next]++;
                }

                indegree[next]--;

                if (indegree[next] == 0) {
                    if (count[next] >= 2) {
                        order[next] = maxOrder[next] + 1;
                    } else {
                        order[next] = maxOrder[next];
                    }
                    q.offer(next);
                }
            }
        }
    }

}
