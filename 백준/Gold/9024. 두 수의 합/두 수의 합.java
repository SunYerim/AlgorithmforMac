import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // two pointer
    static int t, n, k;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // n개의 정수
            k = Integer.parseInt(st.nextToken()); // k에 가장 가까운 두 정수

            numbers = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                numbers[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(numbers);

            int left = 0, right = n - 1;
            int minDiff = Integer.MAX_VALUE; // 최소 합
            int count = 0;

            while (left < right) {
                int total = numbers[left] + numbers[right];
                int diff = Math.abs(total - k);

                if (diff < minDiff) {
                    minDiff = diff;
                    count = 1;
                } else if (diff == minDiff) {
                    count++;
                }

                if (total < k) {
                    left++;
                } else {
                    right--;
                }
            }

            System.out.println(count);

        }

    }

}
