import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea10505 {
    static int person;
    static int[] moneys;
    static int total = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            moneys = new int[N];
            person = 0;
            total = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                moneys[i] = Integer.parseInt(st.nextToken());
                total += moneys[i];
            }

            Arrays.sort(moneys);

            for (int i = 0; i < N; i++) {

                if (moneys[i] <= total / N){
                    person++;
                }
            }

            System.out.println("#"+tc+" "+person);

        }
    }
}
