import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea12368 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            int current = 0; //
            StringTokenizer st = new StringTokenizer(br.readLine());
            current = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            if (current + next == 24) {
                System.out.println("#"+i+" "+0);
            } else if (current+next > 24) {
                System.out.println("#"+i+" "+(current+next-24));
            } else {
                System.out.println("#"+i+" "+(current+next));
            }
        }
    }
}
