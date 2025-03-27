import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer = Integer.MIN_VALUE;
    static int[] arr, counts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        counts = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;

        while (left <= right) {
            // 조건충족 right++
            if (right < N && counts[arr[right]] < K) {
                counts[arr[right]]++;
                right++;
            }
            // 아닌 경우 left++
            else if (counts[arr[right]] == K) {
                counts[arr[left]]--;
                left++;
            }
            answer = Math.max(answer, right - left);

            if (right == N) {
                break;
            }
        }

        System.out.println(answer);
    }

}
