import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, L, R, X, ans = 0;
    static boolean[] visited;
    static List<Problem> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(new Problem(i, Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        for (int i = 2; i <= N; i++) {
            visited = new boolean[N];
            dfs(i, 0, 0);
        }

        System.out.println(ans);

    }

    public static void dfs(int target, int cnt, int start) {
        // 기저
        if (cnt == target) {
            // 난이도 합
            int sum = 0;
            List<Integer> prob = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    prob.add(i);
                    sum += list.get(i).level;
                }
            }

            // 난이도 차이
            int diff = list.get(prob.get(prob.size() - 1)).level - list.get(prob.get(0)).level;

            if (sum >= L && sum <= R && (diff >= X)) {
                ans++;
            }
            return;
        }

        // 유도
        for (int i = start ; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(target, cnt + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static class Problem implements Comparable<Problem> {

        int idx, level;

        public Problem(int idx, int level) {
            this.idx = idx;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            return this.level - o.level;
        }
    }

}
