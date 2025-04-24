import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = N - 1;
        int min = Integer.MAX_VALUE;

        while (start < end) {
            int sum = (arr[start] + arr[end]);

            if (Math.abs(sum) <= Math.abs(min)) {
                min = sum;
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(min);
    }

}
