import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, ans = Integer.MIN_VALUE;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        // dfs
        dfs(N, 0);

        System.out.println(ans);
    }

    public static void dfs(int cnt, int total) {
        // 기저
        if (cnt == 2) {
            ans = Math.max(total, ans);
            return;
        }

        // 유도
        for (int i = 1; i < list.size() - 1; i++) {
            int s = list.get(i - 1) * list.get(i + 1); // 양 옆 곱한 값
            int tmp = list.get(i);
            list.remove(i); // 없애고
            dfs(cnt - 1, total + s);
            list.add(i, tmp); // 다시 넣고
        }

    }


}
