import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합 문제 -> 3종류를 고르는 것이 관건
public class Main {
    static int n, m, ans;
    static int[][] chickens;
    static boolean[] isSelected;
    static int[] selected;
    static int maxNum = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        chickens = new int[n][m];
        selected = new int[3]; //선택된 치킨 집
        isSelected = new boolean[m];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                chickens[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(maxNum);

    }

    private static void combination(int cnt, int start) {
        // 기저
        if (cnt == 3) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int tmp = 0;
                for (int j = 0; j < 3; j++) {
                    tmp = Math.max(tmp, chickens[i][selected[j]]);
                }
                sum += tmp;
            }
            maxNum = Math.max(sum, maxNum);
            return;
        }
        // 유도
        for (int i = start; i < m; i++) {
            selected[cnt] = i;
            combination(cnt+1, i+1);
        }

    }
}