import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] balls;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        balls = new int[n];
        for (int i = 0; i < n; i++) {
            balls[i] = i+1;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //swap
            int tmp = balls[a-1];
            balls[a-1] = balls[b-1];
            balls[b-1] = tmp;


        }
        for (int j = 0; j < n; j++) {
            System.out.print(balls[j]+" ");
        }



    }
}