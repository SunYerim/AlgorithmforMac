import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> graph = new ArrayList<>();
    static int[] visited;
    static int count = 1;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i), Collections.reverseOrder());
        }

        visited = new int[N + 1];

        bfs(R);

        for (int i = 1; i <= N; i++) {
            sb.append(visited[i]).append("\n");
        }

        System.out.print(sb.toString());


    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();

        visited[start] = count++;
        q.add(start);

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : graph.get(curr)) {
                if (visited[next] == 0) {
                    visited[next] = count++;
                    q.add(next);
                }
            }
        }
    }
}
