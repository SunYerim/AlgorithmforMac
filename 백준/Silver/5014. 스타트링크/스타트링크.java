import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int f, s, g, u, d;
    static boolean[] visited;
    static int[] move;
    static int[] pos = new int[2]; // u, d, 담는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        // 범위를 쪼개서
        // 탐색 + 최소한의 버튼 -> bfs
        pos = new int[]{u, -d}; // 아래로 감
        move = new int[f+1]; // 층
        visited = new boolean[f+1];

        bfs(s);

    }

    private static void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();

        q.add(s);
        visited[s] = true;
        move[s] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            // 기저
            if (now == g) {
                System.out.println(move[now]);
                return;
            }

            // 그게 아니면
            for (int i = 0; i < 2; i++) {
                int next = now + pos[i];
                // 범위내에
                if (next <= f && next >= 1 && !visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    // 버튼 누르는 수 -> +1
                    move[next] = move[now] + 1;
                }
            }
        }
        System.out.println("use the stairs");
    }
}