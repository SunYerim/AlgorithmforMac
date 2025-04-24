import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 1. backtraking
    // 2. depth == K일때, max값 갱신
    static boolean[] visited;
    static int N, K, ans = 0;
    static String[] words;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[26];
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        if (K < 5) {
            System.out.println(0);
        } else if (K == 26) {
            System.out.println(N);

        } else {
            visited['a' - 97] = true;
            visited['n' - 97] = true;
            visited['t' - 97] = true;
            visited['i' - 97] = true;
            visited['c' - 97] = true;

            dfs(5, 0);

            System.out.println(ans);
        }

        
    }

    public static void dfs(int depth, int pos) {
        // 기저
        if (depth == K) {
            int num = 0;
            for (int i = 0; i < words.length; i++) {
                boolean flag = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if (!visited[words[i].charAt(j) - 97]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    num++;
                }
            }
            ans = Math.max(ans, num);
            return;
        }

        // 유도
        for (int i = pos; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i);
                visited[i] = false;
            }
        }


    }

}
