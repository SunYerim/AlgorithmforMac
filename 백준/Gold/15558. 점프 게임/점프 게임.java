import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, k, ans;
    static boolean[][] visited; // 이동 x, 그리고 방문 여부 동시 관리
    static int[][] board;
    static int[] d = {1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[2][N + 1];
        visited = new boolean[2][N + 1];

        String line1 = br.readLine();
        String line2 = br.readLine();

        for (int i = 1; i <= N; i++) {
            board[0][i] = line1.charAt(i - 1) - '0';
            board[1][i] = line2.charAt(i - 1) - '0';
        }

        go(0, 1);

        System.out.println(ans);
    }

    public static void go(int line, int pos) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{line, pos, 0});
        visited[line][pos] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int curLine = curr[0];
            int curPos = curr[1];
            int time = curr[2];

            if (curPos <= time) {
                continue;
            }

            if (curPos > N) {
                ans = 1;
                return;
            }

            for (int i = 0; i < 2; i++) {
                // 같은 줄에서 이동
                int nextPos = curPos + d[i];
                if (nextPos <= N && board[curLine][nextPos] == 1
                    && !visited[curLine][nextPos]
                    && nextPos > time + 1
                ) {
                    visited[curLine][nextPos] = true;
                    queue.add(new int[]{curLine, nextPos, time + 1});
                }
                if (nextPos > N) {
                    queue.add(new int[]{curLine, nextPos, time + 1});
                }
            }

            // 옆 줄로 이동
            int nextLine = 1 - curLine;
            int nextPos = curPos + k;
            if (nextPos <= N && board[nextLine][nextPos] == 1 && !visited[nextLine][nextPos]
                && nextPos > time + 1
            ) {
                visited[nextLine][nextPos] = true;
                queue.add(new int[]{nextLine, nextPos, time + 1});
            }
            if (nextPos > N) {
                queue.add(new int[]{nextLine, nextPos, time + 1});
            }
        }

    }

}
