import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea11315 {
    static char[][] board;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            board = new char[N][N];

            for (int i = 0; i < N; i++){
                String string = br.readLine();
                for (int j = 0; j < N; j++) {
                    board[i][j] = string.charAt(j);
                }
            }

            if (isOmok(board)){
                System.out.println("#"+tc+" "+"YES");
            } else {
                System.out.println("#"+tc+" "+"NO");
            }

            // 가로 세로 대각선 중 5개 이상 연속해서 돌이 나오는 경우 있으면 true

        }
    }

    public static boolean isOmok(char[][] board) {
        // 가로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-4; j++) {
                if (board[i][j] == 'o' && board[i][j+1] == 'o' && board[i][j+2] == 'o' && board[i][j+3] == 'o' && board[i][j+4] == 'o')
                    return true;
            }
        }
        // 세로
        for (int i = 0; i < N-4; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'o' && board[i+1][j] == 'o' && board[i+2][j] == 'o' && board[i+3][j] == 'o' && board[i+4][j] == 'o')
                    return true;
            }
        }

        // 대각선 방향 1
        for (int i = 0; i < N-4; i++) {
            for (int j = 0; j < N-4; j++) {
                if (board[i][j] == 'o' && board[i+1][j+1] == 'o' && board[i+2][j+2] == 'o' && board[i+3][j+3] == 'o' && board[i+4][j+4] == 'o')
                    return true;
            }
        }

        // 대각선 방향 2
        for (int i = 0; i < N-4; i++) {
            for (int j = N-1; j >= 4; j--) {
                if (board[i][j] == 'o' && board[i + 1][j - 1] == 'o' && board[i + 2][j - 2] == 'o' && board[i + 3][j - 3] == 'o' && board[i + 4][j - 4] == 'o') {
                    return true;
                }
            }
        }

        return false;
    }

}
