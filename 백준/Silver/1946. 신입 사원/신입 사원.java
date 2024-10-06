import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Rank> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.add(new Rank(a, b));
            }

            Collections.sort(list);

            int ans = 1;
            int min = list.get(0).interview;
            for (int i = 1; i < n; i++) {
                if (list.get(i).interview < min) {
                    ans++;
                    min = list.get(i).interview;
                }
            }
            sb.append(ans).append("\n");

        }
        System.out.println(sb.toString());


    }

    // 서류부터
    static class Rank implements Comparable<Rank> {
        int documents;
        int interview;

        Rank(int documents, int interview) {
            this.documents = documents;
            this.interview = interview;
        }

        @Override
        public int compareTo(Rank o) {
            return this.documents - o.documents;
        }
    }
}