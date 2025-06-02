import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static double[] arr;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static double result = 0.0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new double[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }

        visited = new boolean[29][29];
        visited[14][14] = true;

        dfs(0, 14, 14, 1.0);

        System.out.println(
            (result == (long) result)
                ? String.format("%.1f", result)
                : String.format("%.20f", result).replaceAll("0+$", "").replaceAll("\\.$", "")
        );

    }

    public static void dfs(int depth, int y, int x, double prob) {
        // 기저
        if (depth == N) {
            result += prob;
//            System.out.println(result + "------" + prob);
            return;
        }

        // 유도
        for (int i = 0; i < 4; i++) {
            if (arr[i] == 0.0) {
                continue;
            }

            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= 29 || nx < 0 || nx >= 29 || visited[ny][nx]) {
                continue;
            }

            visited[ny][nx] = true;
            dfs(depth + 1, ny, nx, prob * arr[i]);
            visited[ny][nx] = false;
        }
    }

}
