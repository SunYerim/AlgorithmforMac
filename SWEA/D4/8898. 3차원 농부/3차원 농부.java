import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int T, N, M, c1, c2;
    static int[] cows;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 소
            M = Integer.parseInt(st.nextToken()); // 말

            st = new StringTokenizer(br.readLine());
            c1 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());

            cows = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cows[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(cows);

            int minDis = Integer.MAX_VALUE;
            int cnt = 0;
            int dis2 = Math.abs(c1 - c2);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int horse = Integer.parseInt(st.nextToken());
                int start = 0;
                int end = N - 1;

                // bs
                while (start <= end) {
                    int mid = (start + end) / 2;
                    int dist = Math.abs(cows[mid] - horse);

                    if (dist < minDis) {
                        minDis = dist;
                        cnt = 1;
                    } else if (dist == minDis) {
                        cnt++;
                    }

                    if (cows[mid] < horse) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(dis2 + minDis).append(" ").append(cnt)
                .append("\n");


        }
        System.out.print(sb.toString());
    }

}
