import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = Integer.MIN_VALUE;
    static int[] developers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        developers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            developers[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;

        while (left < right) {
            int numBetween = right - left - 1;
            int minAbility = Math.min(developers[left], developers[right]);
            int ability = numBetween * minAbility;
            answer = Math.max(answer, ability);

            // 더 작은 값 쪽을 좁혀감.
            if (developers[left] < developers[right]) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(answer);
    }

}
