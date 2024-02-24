import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] numbers;
    static int[] pickList;
    static boolean[] isSelected; // node클래스로 묶어서 관리?
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        numbers = new int[n+1];
        pickList = new int[m];
        isSelected = new boolean[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        permutation(0);

        System.out.println(sb);
    }
    private static void permutation(int cnt) {
        // 기저
        if (cnt == m) {
            if (!isDuplicate()) {
                for (int i = 0; i < m; i++) {
                    sb.append(pickList[i]).append(" ");
                }
                sb.append('\n');
            }
            return;
        }
        // 유도
        int beforeValue = -1;
        for (int i = 1; i <= n; i++) {
            if (!isSelected[i] && (beforeValue != numbers[i])) {
                isSelected[i] = true;
                pickList[cnt] = numbers[i];
                permutation(cnt+1);
                isSelected[i] = false;
                beforeValue = numbers[i];
            }

        }
    }

    private static boolean isDuplicate() {
        String tmp = sb.toString();
        String check ="";
        for (int i = 0; i < m; i++) {
            check += pickList[i] + " ";
        }
        check += '\n';
        return tmp.contains(check);
    }

}