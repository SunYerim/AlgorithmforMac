import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    // backtraking
    static Set<String> set = new HashSet<>();
    static String target;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();

        int len = target.length();

        // 시작
        for (int i = 0; i < len; i++) {
            dfs(i, i, String.valueOf(target.charAt(i)), String.valueOf(target.charAt(i)));
        }

//        List<String> ans = new ArrayList<>(set);
//
//        for (int i = 0; i < set.size(); i++) {
//            System.out.println(ans.get(i));
//        }

        System.out.println(set.size());
    }

    public static void dfs(int left, int right, String curr, String level) {

        if (curr.equals(target)) {
            set.add(level);
            return;
        }

        if (left - 1 >= 0) {
            char ch = target.charAt(left - 1);
            dfs(left - 1, right, ch + curr, level + " " + (ch + curr));
        }

        if (right + 1 < target.length()) {
            char ch = target.charAt(right + 1);
            dfs(left, right + 1, curr + ch, level + " " + (curr + ch));
        }
    }
}
