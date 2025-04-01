import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, answer = Integer.MAX_VALUE;
    private static int[] gomduri, s;
    private static Color[] colors;
    private static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        colors = new Color[N];
        gomduri = new int[3];
        selected = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            colors[i] = new Color(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            gomduri[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        System.out.println(answer);

    }

    public static void dfs(int depth, int cnt) {
        // 기저
        if (cnt >= 2) {
            // 계산
            int[] munduri = calculate(selected, cnt);
            answer = Math.min(answer, diff(munduri, gomduri));
        }

        for (int i = depth; i < N; i++) {
            if (selected[i] || cnt > 6) {
                continue;
            }
            selected[i] = true;
            // r, g, b값 계산
            dfs(i + 1, cnt + 1);
            selected[i] = false;
        }
    }

    public static int diff(int[] mun, int[] gom) {
        return Math.abs(mun[0] - gom[0]) + Math.abs(mun[1] - gom[1]) + Math.abs(mun[2] - gom[2]);
    }

    public static int[] calculate(boolean[] visited, int cnt) {
        int[] ans = new int[3];
        int newR = 0;
        int newG = 0;
        int newB = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                newR += colors[i].r;
                newG += colors[i].g;
                newB += colors[i].b;
            }
        }

        ans[0] = newR / cnt;
        ans[1] = newG / cnt;
        ans[2] = newB / cnt;

        return ans;
    }

    static class Color {

        int r, g, b;

        public Color(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
}
