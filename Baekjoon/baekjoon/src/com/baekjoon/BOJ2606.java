package com.baekjoon;
/*logic
    1. */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2606 {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int N, M;
    static int count = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        //st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());
        visited = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        dfs(1);

        System.out.println(count - 1);
    }

    private static int dfs(int x) {
        visited[x] = true;
        count++;
        for (int next : graph.get(x)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
        return count;
    }

}
