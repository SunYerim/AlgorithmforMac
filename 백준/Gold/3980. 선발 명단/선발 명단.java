import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, answer;
    static int[][] board;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            board = new int[11][11];
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MIN_VALUE;
            visited = new boolean[11];
            dfs(0, 0);

            sb.append(answer).append("\n");
        }
        System.out.print(sb.toString());

    }

    public static void dfs(int depth, int score) {
        // 기저
        if (depth == 11) {
            answer = Math.max(answer, score);
            return;
        }
        // 유도
        // 해당 자리에 들어갈 수 있는 것들 넣고 빼고
        for (int i = 0; i < 11; i++) {
            if (!visited[i] && board[depth][i] != 0) {
                visited[i] = true;
                dfs(depth + 1, score + board[depth][i]);
                visited[i] = false;
            }
        }

    }

}
