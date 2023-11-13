import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea3431 {
    public static void main(String[] args) throws IOException {
        // 1주일에 L분 이상, U분 이하의 운동
        // 준환이는 X분 만큼 운동
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            // l보다 작으면
            if (x < l) {
                System.out.println("#"+i+" "+(l-x));
            }
            // l과 u의 사이이면
            else if (x >= l && x <= u) {
                System.out.println("#"+i+" "+0);
            }
            // u보다 많으면
            else {
                System.out.println("#"+i+" "+-1);
            }
        }
    }
}
