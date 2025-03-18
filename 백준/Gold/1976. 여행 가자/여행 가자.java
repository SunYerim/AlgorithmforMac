import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parents;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        // 부모테이블 초기화
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        board = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (board[i][j] == 1) union(i, j);
            }
        }

        // debugging
//        for (int i = 1; i <= N; i++) {
//            System.out.print(parents[i] + " ");
//        }

        // 입력받음
        int[] tmp = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            tmp[i] = findParent(num);
        }

        // 부모 모두 동일하면 true
        Arrays.sort(tmp);
        boolean flag = true;
        int num = tmp[0];
        for (int i = 1; i < M; i++) {
            if (num != tmp[i]) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    private static int findParent(int x) {
        if (parents[x] == x) {
            return x;
        }
        return findParent(parents[x]);
    }

    private static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

}
