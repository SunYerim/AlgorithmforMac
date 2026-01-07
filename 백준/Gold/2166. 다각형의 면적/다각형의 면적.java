import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] x = new long[N];
        long[] y = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        // 신발끈 공식
        long sumA = 0;
        long sumB = 0;

        for (int i = 0; i < N; i++) {
            int next = (i + 1) % N;
            sumA += x[i] * y[next];
            sumB += y[i] * x[next];
        }

        double ans = Math.abs(sumA - sumB) / 2.0;
        System.out.printf("%.1f", ans);

    }

}
