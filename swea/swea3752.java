import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

// ..........ㅎ
public class swea3752 {
    static int N;
    static int[] scores;
    static HashSet<Integer> set;
    static ArrayList<Integer> arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            scores = new int[N];
            set = new HashSet<>();
            arr = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
            }

            set.add(0);
            arr.add(0);
            check(0);

            System.out.println("#" + tc + " " + set.size());
        }


    }
    public static void check(int cnt) {
        if (cnt == N) {
            return;
        }
        int len = arr.size();
        for (int i = 0; i < len; i++) {
            if (!set.contains(arr.get(i)+scores[cnt])) {
                set.add(arr.get(i) + scores[cnt]);
                arr.add(arr.get(i) + scores[cnt]);
            }
        }
        check(cnt+1);
    }
}
