import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int number;

        for (int i = 0; i < n; i++) {
            number = Integer.parseInt(br.readLine());
            dfs(number, 1, 1, 1, 0, "1");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int max, int depth, int num ,int sign, int sum, String tmp) {
        // 기저
        if (depth == max) {
            sum += (num * sign);
            if (sum == 0) {
                sb.append(tmp + "\n");
            }
            return;
        }

        dfs(max, depth+1, num*10 + (depth+1), sign, sum, tmp + " " + String.valueOf(depth+1));

        dfs(max, depth+1, depth+1, 1, sum + (num * sign), tmp + "+" + String.valueOf(depth+1));
        dfs(max, depth+1, depth+1, -1, sum + (num * sign), tmp + "-" + String.valueOf(depth+1));
    }
}