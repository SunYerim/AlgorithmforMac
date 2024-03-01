import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // dfs 활용 문제
    static int n, maxNum, minNum;
    static int[] numbers;
    static int[] operators; // 덧셈, 뺄셈, 곱셈, 나눗셈의 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        operators = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        maxNum = Integer.MIN_VALUE;
        minNum = Integer.MAX_VALUE;

        dfs(1, numbers[0], operators[0], operators[1], operators[2], operators[3]);

        System.out.println(maxNum);
        System.out.println(minNum);
    }

    private static void dfs(int depth, int total, int add, int sub, int mul, int div) {
        // 기저
        if (depth == n) {
            // 값 갱신
            maxNum = Math.max(maxNum, total);
            minNum = Math.min(minNum, total);
            return;
        }

        // 유도
        // 덧셈
        if (add > 0) {
            dfs(depth+1, total + numbers[depth], add-1, sub, mul, div);
        }


        // 뺄셈
        if (sub > 0) {
            dfs(depth+1, total - numbers[depth], add, sub-1, mul, div);
        }

        // 곱셈
        if (mul > 0) {
            dfs(depth+1, total * numbers[depth], add, sub, mul-1, div);
        }

        // 나눗셈
        if (div > 0) {
            dfs(depth+1, total / numbers[depth], add, sub, mul, div-1);
        }

    }
}