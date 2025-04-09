import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] board, prefix;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        prefix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // prefix테이블 처리
        prefix[0][0] = board[0][0];
        // 첫 행, 첫 열
        for (int i = 1; i < N; i++) {
            prefix[i][0] = prefix[i - 1][0] + board[i][0];
        }
        for (int j = 1; j < M; j++) {
            prefix[0][j] = prefix[0][j - 1] + board[0][j];
        }

        // 나머지
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                prefix[i][j] =
                    prefix[i - 1][j] + prefix[i][j - 1] + board[i][j] - prefix[i - 1][j - 1];
            }
        }

        // return
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int total = calculate(x1, y1, x2, y2);
            sb.append(total).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static int calculate(int x1, int y1, int x2, int y2) {
        int ans = prefix[x2][y2];
        // 경계값과 경계값 아닌 것 구분
        if (x1 > 0) {
            ans -= prefix[x1 - 1][y2];
        }
        if (y1 > 0) {
            ans -= prefix[x2][y1 - 1];
        }
        if (x1 > 0 && y1 > 0) {
            ans += prefix[x1 - 1][y1 - 1];
        }

        return ans;
    }

}
