import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long[] muscles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        muscles = new long[n];

        for (int i = 0; i < n; i++) {
            muscles[i] = Long.parseLong(st.nextToken());
        }
        // 정렬
        Arrays.sort(muscles);

        // 1 40 41 42 같은 경우 고려해야 함.
        long res = -1;
        int length = n / 2;

        if (n % 2 == 0){
            for (int i = 0; i < length; i++) {
                res = Math.max(res, muscles[i] + muscles[n-1-i]);
            }
        } else {
            if (n==1) {
                System.out.println(muscles[0]);
                return;
            }
            // 마지막 요소는 마지막에 비교
            for (int i = 0; i < length; i++) {
                res = Math.max(res, muscles[i] + muscles[n-2-i]);
            }
            res = Math.max(res, muscles[n-1]);
        }

        System.out.println(res);
    }
}