import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, score, p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        score = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        ArrayList<Integer> scores = new ArrayList<>();

        if (n != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                scores.add(tmp);
            }
        }


        int rankCount = 1;
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (scores.get(i) > score) rankCount++;
            else if (scores.get(i).equals(score))
                rankCount = rankCount;
            else break;
            cnt++;
        }

        if (n == 0) rankCount = 1;
        if (cnt >= p) rankCount = -1;

        System.out.println(rankCount);
    }
}