import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static int[] level, prefix;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        level = new int[N + 1];
        prefix = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }

        // prefix 전처리
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1];
            if (level[i - 1] > level[i]) {
                prefix[i]++;
            }
        }

        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (start == end) {
                sb.append("0").append("\n");
            } else {
                sb.append(prefix[end] - prefix[start]).append("\n");
            }
        }

        System.out.print(sb.toString());

    }

}
