import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가운데 위치하고 있는 숫자가 최댓값도 최솟값도 아닌 middle값인 경우 count++;
public class swea11736 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] test = new int[N];
            int count = 0; // return해줄 변수

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                test[j] = Integer.parseInt(st.nextToken());
            }


            for (int k = 1; k < N-1; k++) {
                if ((test[k] < test[k-1] && test[k] > test[k+1]) || test[k] > test[k-1] && test[k+1] > test[k])
                    count++;
            }

            System.out.println("#"+i+" "+count);
        }
    }
}
