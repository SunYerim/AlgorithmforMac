import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int ans = -1;

        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N + 1][N + 1];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r1, c1, 0});
        visited[r1][c1] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == r2 && cur[1] == c2) {
                ans = cur[2];
                break;
            }

            for (int i = 0; i < 6; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int cnt = cur[2];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, cnt + 1});
            }
        }

        System.out.println(ans);


    }

}
