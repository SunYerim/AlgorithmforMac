import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, maxNum = Integer.MIN_VALUE, minNum = Integer.MAX_VALUE;
    static int[] numbers;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        count = new int[4]; // 연산자 갯수 -> +, -, *, /

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, numbers[0], count[0], count[1], count[2], count[3]); // depth, total

        System.out.println(maxNum);
        System.out.println(minNum);
    }

    private static void dfs(int depth, int total, int plus, int minus, int multi, int div) {
        // 기저
        if (depth == N) {
            minNum = Math.min(minNum, total);
            maxNum = Math.max(maxNum, total);
            return;
        }

        // 유도
        if (plus > 0) {
            dfs(depth + 1, total + numbers[depth], plus - 1, minus, multi, div);
        }
        if (minus > 0) {
            dfs(depth + 1, total - numbers[depth], plus, minus - 1, multi, div);
        }
        if (multi > 0) {
            dfs(depth + 1, total * numbers[depth], plus, minus, multi - 1, div);
        }
        if (div > 0) {
            dfs(depth + 1, total / numbers[depth], plus, minus, multi, div - 1);
        }

    }

}
