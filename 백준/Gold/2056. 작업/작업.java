import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] indegree, time;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        indegree = new int[n+1];
        time = new int[n+1];
        list = new ArrayList<>();

        // list초기화
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                list.get(tmp).add(i);

                indegree[i]++;
            }

        }

        int answer = topologySort(n, list, indegree, time);
        System.out.println(answer);
    }

    private static int topologySort(int n, ArrayList<ArrayList<Integer>> list, int[] indegree, int[] time) {
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[n+1];

        // 진입차수 0인 노드 삽입
        for (int i = 1; i <= n; i++) {
            result[i] = time[i];
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 큐가 비지않을동안
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : list.get(now)) {
                indegree[next]--;

                result[next] = Math.max(result[next], result[now] + time[next]);

                if (indegree[next] == 0)
                    queue.offer(next);
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, result[i]);
        }
        return ans;
    }
}