import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] b = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        // b -> a
        int ans = 0;
        while (true) {
            boolean allZero = true;
            for (int i = 0; i < N; i++) {
                if (b[i] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                break;
            }

            // 홀수 존재
            boolean oddExists = false;
            for (int i = 0; i < N; i++) {
                if (b[i] % 2 != 0) {
                    b[i]--;
                    ans++;
                    oddExists = true;
                }
            }

            // 짝수 존재
            if (!oddExists) {
                for (int i = 0; i < N; i++) {
                    b[i] /= 2;
                }
                ans++;
            }

        }
        System.out.println(ans);
    }

}
