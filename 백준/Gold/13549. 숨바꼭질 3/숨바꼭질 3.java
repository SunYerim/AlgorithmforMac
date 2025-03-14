import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, k, answer = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] dx = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        
        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        // n에서 시작합니다.
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0}); // 현 위치와 시간
        visited[n] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int time = cur[1];

            // 기저 -> 도착
            if (x == k) {
                answer = Math.min(answer, time);
                return;
            }

            // 순간이동
            if (2 * x <= 100000 && !visited[2 * x]) {
                visited[2 * x] = true;
                queue.add(new int[]{2 * x, time});
            }

            // 그냥 이동
            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                if (nx >= 0 && nx <= 100000 && !visited[nx]) {
                    visited[nx] = true;
                    queue.add(new int[]{nx, time + 1});
                }
            }
        }
    }

}
