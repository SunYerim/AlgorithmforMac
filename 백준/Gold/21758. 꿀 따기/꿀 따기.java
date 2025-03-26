import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 누적합
    static int[] sumArr, list;
    static int N, answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        list = new int[N];
        sumArr = new int[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        sumArr[0] = list[0];
        for (int i = 1; i < N; i++) {
            sumArr[i] = sumArr[i - 1] + list[i];
        }

        // 꿀벌벌
        for (int i = 1; i < N - 1; i++) {
            int bee1 = sumArr[i-1];
            int bee2 = sumArr[N-2] - list[i];
            answer = Math.max(answer, bee1 + bee2);
        }


        // 벌꿀벌
        for (int i = 1; i < N-1; i++) {
            int bee1 = sumArr[i] - list[0];
            int bee2 = sumArr[N - 2] - sumArr[i-1];
            answer = Math.max(answer, bee1 + bee2);
        }

        // 벌벌꿀
        for (int i = 1; i < N-1; i++) {
            int bee1 = sumArr[N-1] - list[i] - sumArr[0];
            int bee2 = sumArr[N-1] - sumArr[i];
            answer = Math.max(answer, bee1 + bee2);
        }

        System.out.println(answer);
    }
}
