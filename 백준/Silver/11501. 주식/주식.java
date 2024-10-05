
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

/*
* */
public class Main {
    static int T, n;
    static int[] prices;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            prices = new int[n];
            st = new StringTokenizer(br.readLine());
            long maxPrice = 0;
            long answer = 0;

            for (int j = 0; j < n; j++) {
                prices[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = n-1; j >= 0; j--) {
                if (prices[j] > maxPrice) {
                    maxPrice = prices[j];
                } else {
                    answer += (maxPrice - prices[j]);
                }
            }


            sb.append(answer).append("\n");

        }
        System.out.println(sb.toString());
    }

}
