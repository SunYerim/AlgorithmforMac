import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long ans;
    static List<List<Node>> list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        // 1번에서 다른 방까지 최대 거리 return
        go(1);

        System.out.println(ans);

    }

    public static void go(int st) {
        Queue<long[]> q = new LinkedList<>();
        q.add(new long[]{st, 0}); // 출발지점, 거리
        visited[1] = true;

        while (!q.isEmpty()) {
            long[] cur = q.poll();
            ans = Math.max(ans, cur[1]);

            for (Node node : list.get((int)cur[0])) {
                if (visited[node.end]) {
                    continue;
                }
                visited[node.end] = true;
                q.add(new long[]{node.end, cur[1] + node.cost});
            }
        }
    }

    static class Node {

        int end;
        long cost;

        Node(int end, long cost) {
            this.end = end;
            this.cost = cost;
        }
    }

}
