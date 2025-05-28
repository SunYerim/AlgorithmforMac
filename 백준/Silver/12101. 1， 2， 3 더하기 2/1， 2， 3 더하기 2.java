import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static List<String> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        dfs(0, "");

        Collections.sort(list);

//        System.out.println(list.size());

        if (list.size() < k) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(k - 1));
        }


    }

    public static void dfs(int sum, String s) {
        if (sum > n) {
            return;
        }

        // 기저
        if (sum == n) {
            list.add(s);
            return;
        }

        // 유도
        for (int i = 1; i <= 3; i++) {
            if (s.isEmpty()) {
                dfs(sum + i, String.valueOf(i));
            } else {
                dfs(sum + i, s + "+" + i);
            }
        }

    }

}
