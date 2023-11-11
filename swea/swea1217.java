import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 1; T <= 10; T++) {

            br.readLine(); // 테스트 케이스 번호 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int answer = power(n, m);

            System.out.println("#"+T+" "+answer);

        }
    }


    public static int power(int x, int y) {
        if (y == 0) {
            return 1;
        }
        return x * power(x, y-1);

    }
}
