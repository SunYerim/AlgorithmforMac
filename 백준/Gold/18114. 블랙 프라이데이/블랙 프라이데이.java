import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        
        // 하나
        for (int i = 0; i < n; i++) {
            if (c == numbers[i]) {
                System.out.println(1);
                return;
            }
        }

        int start = 0;
        int end = n - 1;

        // 두 수
        while (start < end) {
            long total = (long) numbers[start] + numbers[end];

            if (total == c) {
                System.out.println(1);
                return;
            } else if (total < c) {
                start++;
            } else {
                end--;
            }
        }

        // 세 수
        for (int i = 0; i < n; i++) {
            start = i + 1;
            end = n - 1;
            long target = (long) c - numbers[i];
            while (start < end) {
                long total = (long) numbers[start] + numbers[end];

                if (total == target) {
                    System.out.println(1);
                    return;
                } else if (total < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        System.out.println(0);

    }

}
