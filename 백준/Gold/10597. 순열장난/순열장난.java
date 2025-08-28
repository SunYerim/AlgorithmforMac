import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        visited = new boolean[51];

        backtraking(line, 0, "");
    }

    public static void backtraking(String line, int idx, String curr) {
        // 기저
        if (idx == line.length()) {
            int n = curr.split(" ").length;
            boolean allUsed = true;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    allUsed = false;
                    break;
                }
            }

            if (allUsed) {
                sb.append(curr.trim());
                System.out.println(sb.toString());
                System.exit(0);
            }
            return;
        }

        // 유도
        // 한 자리
        if (idx < line.length()) {
            int num1 = Integer.parseInt(line.substring(idx, idx + 1));
            if (num1 >= 1 && num1 <= 9 && !visited[num1]) {
                visited[num1] = true;
                backtraking(line, idx + 1, curr + num1 + " ");
                visited[num1] = false;
            }
        }

        // 두 자리
        if (idx + 1 < line.length()) {
            int num2 = Integer.parseInt(line.substring(idx, idx + 2));
            if (num2 >= 10 && num2 <= 50 && !visited[num2]) {
                visited[num2] = true;
                backtraking(line, idx + 2, curr + num2 + " ");
                visited[num2] = false;
            }

        }
    }

}
