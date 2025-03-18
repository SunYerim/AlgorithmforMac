import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 친구관계 표시
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // 친구관계 확인
        boolean flag = true;

        for (int i = 1; i <= N; i++) {
            boolean friend = bfs(i);
            if (!friend) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("Small World!");
        } else {
            System.out.println("Big World!");
        }
    }

    private static boolean bfs(int idx) {
        boolean flag = true;
        boolean[] visited = new boolean[N + 1];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{idx, 0});
        visited[idx] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currIdx = curr[0];
            int currCnt = curr[1];

            if (currCnt >= 6) break;

            for (int friend : graph.get(currIdx)) {
                if (!visited[friend]) {
                    queue.offer(new int[]{friend, currCnt + 1});
                    visited[friend] = true;
                }
            }
        }

        // check
        for (int x = 1; x <= N; x++) {
            if (x == idx) continue;
            else if (!visited[x]) flag = false;
        }

        return flag;
    }

}
