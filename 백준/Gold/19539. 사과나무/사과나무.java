import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        trees = new int[N];
        st = new StringTokenizer(br.readLine());

        int total = 0;
        int twoCnt = 0;

        for (int i = 0; i < N; i++) {
            int tree = Integer.parseInt(st.nextToken());
            trees[i] = tree;
            total += tree;
            twoCnt += tree / 2;
        }

        if (total % 3 == 0 && twoCnt >= total / 3) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

}
