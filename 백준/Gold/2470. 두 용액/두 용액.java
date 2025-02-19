import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] answer, liquids;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        answer = new int[2];
        liquids = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquids);

        int start = 0, end = N - 1;
        int total = Integer.MAX_VALUE;

        while (start < end) {
            int sum = liquids[start] + liquids[end];

            if (Math.abs(sum) < total) {
                total = Math.abs(sum);
                answer[0] = liquids[start];
                answer[1] = liquids[end];
            }

            if (sum == 0) break;
            else if (sum < 0) start += 1;
            else end -= 1;
        }

        System.out.println(answer[0] + " " + answer[1]);
    }

}