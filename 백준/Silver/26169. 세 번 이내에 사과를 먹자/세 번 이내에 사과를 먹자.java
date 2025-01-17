import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static int r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        boolean possible = backtracking(board, r, c, 0, 0); // cnt, apple

        if (possible) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    // backtracking
    private static boolean backtracking(int[][] board, int r, int c, int cnt, int apple) {
        if (r < 0 || r >= 5 || c < 0 || c >= 5 || board[r][c] == -1 || cnt > 3) {
            return false;
        }

        if (board[r][c] == 1) {
            apple++;
        }

        if (apple >= 2) {
            return true;
        }

        int tmp = board[r][c];
        board[r][c] = -1;

        boolean result =
            backtracking(board, r + 1, c, cnt + 1, apple) || backtracking(board, r - 1, c, cnt + 1,
                apple) || backtracking(board, r, c + 1, cnt + 1, apple) || backtracking(board, r,
                c - 1, cnt + 1, apple);

        board[r][c] = tmp;

        return result;

    }

}