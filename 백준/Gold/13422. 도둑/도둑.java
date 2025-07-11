import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 집의 개수
            int M = Integer.parseInt(st.nextToken()); // 연속된 집의 개수
            int K = Integer.parseInt(st.nextToken()); // 방범 장치 작동하는 최소 돈의 양

            int[] arr = new int[N + M - 1];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = N; j < N + M - 1; j++) {
                arr[j] = arr[j - N];
            }

            int ans = 0;
            long currSum = 0;

            // sliding window
            // init
            for (int j = 0; j < M; j++) {
                currSum += arr[j];
            }

            if (N == M) {
                if (currSum < K) {
                    ans = 1;
                }
            } else {
                if (currSum < K) {
                    ans++;
                }

                for (int start = 1; start < N; start++) {
                    int end = start + M - 1;
                    currSum -= arr[start - 1];
                    currSum += arr[end];

                    if (currSum < K) {
                        ans++;
                    }
                }
            }

            sb.append(ans).append("\n");

        }
        System.out.print(sb.toString());
    }

}
