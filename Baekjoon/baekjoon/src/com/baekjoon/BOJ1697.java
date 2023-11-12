package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {
    static int N, K;
    static int[] visited = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = bfs(N);
        System.out.println(result);



    }
    public static int bfs(int node) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(node);
        int index = node;
        //int n = 0;
        visited[index] = 1;
        while (!q.isEmpty()) {
            int n = q.remove();
            if (n==K) {
                return visited[n] - 1;
            }
            // 0보다 크면서 방문하지 않았을때,
            if (n-1 >= 0 && visited[n-1] == 0) {
                visited[n-1] = visited[n] + 1;
                q.add(n-1);
            }
            if (n+1 <= 100000 && visited[n+1] == 0) {
                visited[n+1] = visited[n] + 1;
                q.add(n+1);
            }
            if (2*n <= 100000 && visited[2*n] == 0) {
                visited[2*n] = visited[n] + 1;
                q.add(2*n);
            }
        }
        return -1;
    }

}
