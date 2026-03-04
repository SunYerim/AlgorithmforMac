import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 0) {
            System.out.println(0);
            return;
        } else if (N > 1022) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            dfs(i, 1);
        }

        Collections.sort(list);

        System.out.println(list.get(N));
    }

    public static void dfs(long num, int length) {
        // 9876543210이 최대
        if (length > 10) {
            return;
        }

        list.add(num);

        long lastNum = num % 10;

        for (int i = 0; i < lastNum; i++) {
            dfs(num * 10 + i, length + 1);
        }
    }

}
