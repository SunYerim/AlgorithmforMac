import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static double ans = 0;
    static int[] numbers, selected;
    static double[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        numbers = new int[n+1];
        selected = new int[m];
        dp = new double[n+1][m+1];

        combination(0, 0);

        System.out.println(ans);

    }

    private static void combination(int cnt, int start) {
        // 기저
        if (cnt == m) {
            // 선택한 m개의 복권 번호 중에서 k개 이상이 당첨번호와 같은 경우
            double sameCase = 0.0;
            for (int i = k; i <= m; i++) {
                if (n-m >= m-i) {
                    sameCase += combination1(m, i) * combination1(n-m, m-i);
                }
            }
            ans = sameCase / combination1(n, m);
            return;
        }
        // 유도
        for (int i = start; i < n; i++) {
            selected[cnt] = numbers[i];
            combination(cnt+1, i+1);
        }
    }

    // 메모이 제이션 기법
    private static double combination1(int n, int r) {
        if (n==r || r==0)
            return 1;
        if (dp[n][r] > 0) // 이미 계산한 적이 있는 경우
            return dp[n][r]; // 저장된 결과 반환
        dp[n][r] = combination1(n-1,r-1) + combination1(n-1,r);
        return dp[n][r];
    }
}