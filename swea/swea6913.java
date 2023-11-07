import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea6913 {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            //int max = -2147000000;
            int[][] problem = new int[N][M];
            int[] result = new int[N];

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    problem[j][k] = Integer.parseInt(st.nextToken());

                    if (problem[j][k] == 1){
                        result[j]++;
                    }

                }

            }
            int cnt = 0;
            int maxNum = Arrays.stream(result).max().getAsInt();
            for (int a : result) {
                if (a == maxNum){
                    cnt++;
                }
            }
            System.out.println("#"+(i+1)+" "+cnt+" "+maxNum);
        }
    }
}
