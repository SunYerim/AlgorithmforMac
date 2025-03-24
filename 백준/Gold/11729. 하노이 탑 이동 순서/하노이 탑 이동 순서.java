import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();
    static List<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        hanoi(N, 1, 3, 2); // from - to - mid

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)[0] + " " + list.get(i)[1]).append("\n");
        }

        System.out.println(list.size());
        System.out.print(sb.toString());

    }

    public static void hanoi(int n, int from, int to, int mid) {
        // 기저
        if (n == 1) {
            list.add(new int[]{from, to});
            return;
        }

        hanoi(n - 1, from, mid, to);
        list.add(new int[]{from, to});
        hanoi(n - 1, mid, to, from);
    }

}
