import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] numbers;
    static int[] pickList;
    static boolean[] isSelected;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n+1];
        pickList = new int[m]; // 뽑힌 숫자들
        isSelected = new boolean[n+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        permutation(0);
        System.out.print(sb);

    }

    private static void permutation(int cnt) {
        // 기저
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                sb.append(pickList[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        // 유도
        for (int i = 1; i <= n; i++) {
            if (isSelected[i])
                continue;
            pickList[cnt] = numbers[i];
            isSelected[i] = true;
            permutation(cnt+1);
            isSelected[i] = false;

        }
    }
}