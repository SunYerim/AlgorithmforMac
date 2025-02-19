import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long[] answer, liquids;
    static long total = 3000000000L;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        answer = new long[3];
        liquids = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquids[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(liquids);

        for (int i = 0; i < N - 2; i++) {
            solution(liquids, i);
        }

        Arrays.sort(answer);

        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);

    }

    private static void solution(long[] liquids, int index) {
        int start = index + 1, end = N - 1;

        while (start < end) {

            long sum = liquids[start] + liquids[index] + liquids[end];
            long absSum = Math.abs(sum);

            if (absSum < total) {
//                System.out.println(liquids[start] + " " + liquids[mid] + " " + liquids[end]);
                total = absSum;
                answer[0] = liquids[start];
                answer[1] = liquids[end];
                answer[2] = liquids[index];
            }

            else if (sum > 0) {
                end -= 1;
            }
            else {
                start += 1;
            }
        }
    }

}