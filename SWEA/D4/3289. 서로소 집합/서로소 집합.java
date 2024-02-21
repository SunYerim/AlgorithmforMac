import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    private static int N, M, T; //   원소 개수
    private static int[] parents; // 각 원소들이 어떤 집합에 속해있는지 기록


    // 서로소 집합 3가지 연산
    private static void makeSet() {
        parents = new int[N+1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {

        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
        //return parents[a] = find(parents[a]); // path compression
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

//        if (a < b) {
//            parents[b] = a;
//        }
//        else {
//            parents[a] = b;
//        }

        if (a==b) {
            return false;
        }

        parents[b] = a;
        return true;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            makeSet();
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int order = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (order == 0) {
                    // 집합을 합친다.
                    union(a, b);
                }

                if (order == 1) {
                    // 집합 확인
                    int parent1 = find(a);
                    int parent2 = find(b);

                    if (parent1 == parent2) {
                        sb.append(1);
                    }
                    else {
                        sb.append(0);
                    }
//                    System.out.println(find(a));

                }
            }

            System.out.println(sb);
        }

    }
}