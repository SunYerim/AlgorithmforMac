import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* logic
    1. 구현이 필요한 메서드
        - 각 테두리를 시계방향으로 돌리는 메서드
        - 배열 돌리는 순서 조합
        - 각 행의 값들 더해서 min값 return해주는*/
public class Main {
    static int n, m, k, min = Integer.MAX_VALUE;
    static int[][] map;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        nodes = new Node[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(r, c, s);
        }

        dfs(0, new int[k], new boolean[k]);

        System.out.println(min);

    }

    private static int[][] copyMap() {
        int[][] newArr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newArr[i][j] = map[i][j];
            }
        }
        return newArr;
    }

    private static void roundMap(int[] arr) {
        int[][] tmp = copyMap();

        for (int tmpk = 0; tmpk < k; tmpk++) {
            int r = nodes[arr[tmpk]].r;
            int c = nodes[arr[tmpk]].c;
            int s = nodes[arr[tmpk]].s;

            for (int a = 1; a <= s; a++) {
                // 위
                int upTmp = tmp[r-a][c+a];
                for (int y = c+a; y > c-a; y--) {
                    tmp[r-a][y] = tmp[r-a][y-1];
                }
                // 오른쪽
                int rightTmp = tmp[r+a][c+a];
                for (int x = r+a; x > r-a; x--) {
                    tmp[x][c+a] = tmp[x-1][c+a];
                }
                tmp[r-a+1][c+a] = upTmp;
                // 아래
                int leftTmp = tmp[r+a][c-a];
                for (int y = c-a; y < c+a; y++) {
                    tmp[r+a][y] = tmp[r+a][y+1];
                }
                tmp[r+a][c+a-1] = rightTmp;
                // 왼쪽
                for (int x = r-a; x < r+a; x++) {
                    tmp[x][c-a] = tmp[x+1][c-a];
                }
                tmp[r+a-1][c-a] = leftTmp;
            }
        }
        getAnswer(tmp);
    }

    private static void getAnswer(int[][] tmp) {
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += tmp[i][j];
            }
            if (min > sum) min = sum;
        }
    }



    // 연산 순서 정하는 메서드
    private static void dfs(int depth, int[] arr, boolean[] visited) {
        // 기저
        if (depth == k) {
            roundMap(arr);
            return;
        }
        // 유도
        for (int i = 0; i < k; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            arr[depth] = i;
            dfs(depth+1, arr, visited);
            visited[i] = false;
        }
    }
    static class Node {
        int r, c, s;
        public Node(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}