import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N;
    static int[] parents;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 역순으로 scan
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            parents = new int[N+1];

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parents[b] = a; // b의 부모는 a
            }

            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            solve(num1, num2);

        }

        System.out.print(sb.toString());

    }

    private static void solve(int num1, int num2) {
        // 역으로 root노드까지 올라가서 mark.
        // num2가 root노드까지 올라가면서 visited = true 지점을 만나면 즉시 break
        boolean[] visited = new boolean[N + 1];

        // num1 -> root
        while (num1 > 0) {
            visited[num1] = true;
            num1 = parents[num1];
        }

        // num2 -> root
        while (num2 > 0) {
            if (visited[num2]) {
                sb.append(num2).append("\n");
                break;
            }
            num2 = parents[num2];
        }
    }

}
