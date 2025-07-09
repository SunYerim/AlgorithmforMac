import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, ans = Integer.MIN_VALUE;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 바꿀 수 있는 조합이 보이면 바꾸고 bfs실행
        // ans를 계속 최댓값으로 갱신한다.
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 초기에서 최대 길이 계산
        checkMaxCandy();

        // 탐색하면서 상하좌우 똑같은 게 있다면
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 오른쪽 사탕과
                if (j + 1 < N) {
                    swap(i, j, i, j + 1);
                    checkMaxCandy();
                    swap(i, j, i, j + 1);
                }

                // 아래쪽 사탕과
                if (i + 1 < N) {
                    swap(i, j, i + 1, j);
                    checkMaxCandy();
                    swap(i, j, i + 1, j);
                }
            }
        }

        System.out.println(ans);

    }

    public static void swap(int r1, int c1, int r2, int c2) {
        char temp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = temp;
    }

    public static void checkMaxCandy() {
        // 행
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = 1; j < N; j++) {
                if (board[i][j] == board[i][j - 1]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                ans = Math.max(ans, cnt);
            }
        }

        // 열
        for (int j = 0; j < N; j++) {
            int cnt = 1;
            for (int i = 1; i < N; i++) {
                if (board[i][j] == board[i - 1][j]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                ans = Math.max(ans, cnt);
            }
        }
    }

}
