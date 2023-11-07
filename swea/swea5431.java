import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea5431 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] count = new int[N];
            for (int j = 0; j < K; j++) {
                int temp = Integer.parseInt(st.nextToken());
                count[temp-1]++;
            }

            // 출력
            //int cnt = 0;
            System.out.print("#"+(i+1)+" ");
            for (int k = 0; k < N; k++) {
                if (count[k] == 0) {
                    System.out.print(k+1+" ");
                }
            }
            System.out.println();
        }
    }
}
