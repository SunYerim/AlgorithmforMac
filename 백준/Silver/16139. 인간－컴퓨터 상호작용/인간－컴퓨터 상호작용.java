import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] prefix;
    static String S;
    static int q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        S = br.readLine();
        q = Integer.parseInt(br.readLine());
        prefix = new int[26][S.length() + 1];

        // 누적합
        for (int i = 0; i < S.length(); i++) {
            int cur = S.charAt(i) - 'a';

            for (int j = 0; j < 26; j++) {
                prefix[j][i + 1] = prefix[j][i];
            }

            prefix[cur][i + 1]++; //  현재 값

        }

        // 선형탐색
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int idx = c - 'a';
            sb.append(prefix[idx][end + 1] - prefix[idx][start]).append("\n");

//            prefix = new int[26][S.length() + 1];
//
//            int cnt = 0;
//            for (int j = 0; j < S.length(); j++) {
//                if (S.charAt(j) == c) {
//                    cnt++;
//                    prefix[j + 1] = cnt;
//                }
//                prefix[j + 1] = cnt;
//            }
//            sb.append(prefix[end + 1] - (prefix[start])).append("\n");

        }
        System.out.print(sb.toString());
    }

}
