import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arrows = new int[1000001];
        int ans = 0;

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());

            if (arrows[h] > 0) {
                arrows[h]--;
                arrows[h - 1]++;
            } else {
                ans++;
                arrows[h - 1]++;
            }
        }

        System.out.println(ans);
    }

}
