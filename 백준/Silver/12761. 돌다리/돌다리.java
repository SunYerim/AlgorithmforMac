import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int A, B, N, M, ans = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] dx = {-1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        // N -> M
        bfs(N, M);

        System.out.println(ans);

    }

    public static void bfs(int st, int end) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{st, 0});
        visited[st] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int cnt = cur[1];

            // 기저
            if (curX == end) {
                ans = Math.min(ans, cnt);
            }

            // 1. +1, -1
            for (int i = 0; i < 2; i++) {
                int nx = curX + dx[i];
                if (nx >= 0 && nx <= 100000 && !visited[nx]) {
                    q.add(new int[]{nx, cnt + 1});
                    visited[nx] = true;
                }
            }

            // 2. +A, -A, +B, -B
            for (int i = 0; i < 2; i++) {
                int nx1 = curX + dx[i] * A;
                int nx2 = curX + dx[i] * B;
                if (nx1 >= 0 && nx1 <= 100000 && !visited[nx1]) {
                    q.add(new int[]{nx1, cnt + 1});
                    visited[nx1] = true;
                }
                if (nx2 >= 0 && nx2 <= 100000 && !visited[nx2]) {
                    q.add(new int[]{nx2, cnt + 1});
                    visited[nx2] = true;
                }
            }

            // 3. *A or *B
            int nx1 = curX * A;
            int nx2 = curX * B;
            if (nx1 >= 0 && nx1 <= 100000 && !visited[nx1]) {
                q.add(new int[]{nx1, cnt + 1});
                visited[nx1] = true;
            }
            if (nx2 >= 0 && nx2 <= 100000 && !visited[nx2]) {
                q.add(new int[]{nx2, cnt + 1});
                visited[nx2] = true;
            }


        }
    }

}
