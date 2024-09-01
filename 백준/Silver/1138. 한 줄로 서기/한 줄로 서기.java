import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] person;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        person = new int[N];
        ans = new int[N];

        for (int i = 0; i < N; i++) {
            person[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int count = person[i];
            for (int j = 0; j < N; j++) {
                if (count == 0 && ans[j] == 0) {
                    ans[j] = i + 1;
                    break;
                } else if (ans[j] == 0) {
                    count--;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}