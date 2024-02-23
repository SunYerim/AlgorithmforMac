import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[] isSelected;; // 현재 뽑은 수 flag배열
    static int[] numbers; // 현재까지 뽑은 수를 저장하는 배열
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        numbers = new int[m]; // 현재까지 뽑은 수를 저장하는 배열
        isSelected = new boolean[n+1]; // 0은 사용하지 않는다.

        permutation(0);
        System.out.print(sb);
    }

    private static void permutation(int cnt) {
        // 기저
        if (cnt == m){
            int tmp = numbers.length;

            for (int i = 0; i < tmp; i++) {
                sb.append(numbers[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 유도
        for (int i = 1; i <= n; i++) {
            numbers[cnt] = i;
            permutation(cnt+1);
        }
    }
}