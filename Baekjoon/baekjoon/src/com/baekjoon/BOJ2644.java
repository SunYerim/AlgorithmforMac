package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2644 {
    static boolean[] visited;
    static int n, from, to, m;
    static int x, y, count;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());

        // 그래프 초기화
        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<Integer>());
        }

        // graph 연결 작업
        for (int i = 1; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);

        }

        // 방문했나 안했나 그래프
        visited = new boolean[n + 1];

        System.out.println(dfs(from, to));


    }

    private static int dfs(int x, int y) {
        if (x == y) {
            return 0;
        }
         // x -> y까지 연결이 되어야함.
        // 방문처리 해주고,
        visited[x] = true;

        for (int neighbor: graph.get(x)) {
            if (!visited[neighbor]) {
                int result = dfs(neighbor, y);
                if (result >= 0) {
                    return result + 1;
                }
            }
        }
        visited[x] = false; // 백트래킹

        // 경로 못 찾았으면 -1 반환
        return -1;

    }
}
